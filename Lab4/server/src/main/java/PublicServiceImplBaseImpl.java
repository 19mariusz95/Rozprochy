import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.Objects;

public class PublicServiceImplBaseImpl extends PublicServiceGrpc.PublicServiceImplBase {

    @Override
    public void listDoctors(Hospital.Request request, StreamObserver<Hospital.Doctors> responseObserver) {
        Hospital.Doctors build = Hospital.Doctors.newBuilder().addAllDoctors(Server.doctors.values()).build();
        responseObserver.onNext(build);
        responseObserver.onCompleted();
    }

    @Override
    public void listLabs(Hospital.Request request, StreamObserver<Hospital.Labs> responseObserver) {
        Hospital.Labs labs = Hospital.Labs.newBuilder().addAllLabs(Server.labs.values()).build();
        responseObserver.onNext(labs);
        responseObserver.onCompleted();
    }

    @Override
    public void registerPatient(Hospital.RegisterRequest request, StreamObserver<Hospital.Patient> responseObserver) {
        Hospital.Person person = Server.registerPerson(request.getFirstName(), request.getLastName());
        Hospital.Patient patient = Hospital.Patient.newBuilder().setPerson(person).build();
        synchronized (Server.patients) {
            Server.patients.put(patient.getPerson().getId(), patient);
        }
        responseObserver.onNext(patient);
        responseObserver.onCompleted();
    }

    @Override
    public void requestPersonalData(Hospital.Request request, StreamObserver<Hospital.Person> responseObserver) {
        Hospital.Person person = null;
        long id = request.getId();
        if(Server.doctors.containsKey(id)){
            person = Server.doctors.get(id).getPerson();
        } else if(Server.labs.containsKey(id)) {
            person = Server.labs.get(id).getPerson();
        } else if(Server.patients.containsKey(id)) {
            person = Server.patients.get(id).getPerson();
        }
        if (Objects.isNull(person)) {
            throw new StatusRuntimeException(Status.NOT_FOUND);
        }
        responseObserver.onNext(person);
        responseObserver.onCompleted();
    }
}
