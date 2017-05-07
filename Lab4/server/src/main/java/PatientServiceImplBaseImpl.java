import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PatientServiceImplBaseImpl extends PatientServiceGrpc.PatientServiceImplBase {

    @Override
    public void requestAllResults(Hospital.Request request, StreamObserver<Hospital.MedicalExams> responseObserver) {
        if(Objects.isNull(Server.doctors.get(request.getId()))) {
            throw new StatusRuntimeException(Status.PERMISSION_DENIED);
        }
        List<Hospital.MedicalExam> medicalExams = Server.exams.stream().filter(e -> e.getPatient().getIdentity().getId() == request.getId()).collect(Collectors.toList());
        Hospital.MedicalExams exams = Hospital.MedicalExams.newBuilder().addAllExams(medicalExams).build();
        responseObserver.onNext(exams);
        responseObserver.onCompleted();
    }


}
