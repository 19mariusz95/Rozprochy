import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.Objects;

public class PatientServiceImplBaseImpl extends PatientServiceGrpc.PatientServiceImplBase {

    @Override
    public void requestAllResults(Hospital.Request request, StreamObserver<Hospital.MedicalExam> responseObserver) {
        if(Objects.isNull(Server.patients.get(request.getId()))) {
            throw new StatusRuntimeException(Status.PERMISSION_DENIED);
        }
        synchronized (Server.exams) {
            Server.exams.stream()
                    .filter(e -> e.getPatient().getPerson().getId() == request.getId())
                    .forEach(responseObserver::onNext);
        }
        responseObserver.onCompleted();
    }


}
