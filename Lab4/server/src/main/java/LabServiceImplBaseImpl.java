import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;
import java.util.Objects;

public class LabServiceImplBaseImpl extends LabServiceGrpc.LabServiceImplBase {

    @Override
    public void addResults(Hospital.AddExamRequest request, StreamObserver<Hospital.Response> responseObserver) {
        checkPerms(request.getLab());
        Hospital.Doctor doctor = Server.doctors.get(request.getDoctor());
        Hospital.Patient patient = Server.patients.get(request.getPatient());
        if(Objects.isNull(doctor) || Objects.isNull(patient)) {
            throw new StatusRuntimeException(Status.NOT_FOUND);
        }
        Hospital.Lab lab = Server.labs.get(request.getLab());
        Hospital.MedicalExam medicalExam = Hospital.MedicalExam.newBuilder()
                .setDoctor(doctor)
                .setLab(lab)
                .setPatient(patient)
                .setTime(LocalDateTime.now().toString())
                .putAllResults(request.getResultsMap())
                .build();
        synchronized (Server.exams) {
            Server.exams.add(medicalExam);
        }
        responseObserver.onNext(Hospital.Response.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void requestAllResultsForLab(Hospital.Request request, StreamObserver<Hospital.MedicalExam> responseObserver) {
        checkPerms(request.getId());
        synchronized (Server.exams) {
            Server.exams.stream().filter(e -> e.getLab().getPerson().getId() == request.getId())
                    .forEach(responseObserver::onNext);
        }
        responseObserver.onCompleted();
    }

    private void checkPerms(long id) {
        if(Objects.isNull(Server.labs.get(id))) {
            throw new StatusRuntimeException(Status.PERMISSION_DENIED);
        }
    }
}
