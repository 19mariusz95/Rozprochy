import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DoctorServiceImplBaseImpl extends DoctorServiceGrpc.DoctorServiceImplBase {

    @Override
    public void requestAllPatients(Hospital.Request request, StreamObserver<Hospital.Patients> responseObserver) {
        checkPermission(request.getId());
        Hospital.Patients patients = Hospital.Patients.newBuilder().addAllPatients(Server.patients.values()).build();
        responseObserver.onNext(patients);
        responseObserver.onCompleted();
    }

    @Override
    public void requestPatientsForDoctor(Hospital.Request request, StreamObserver<Hospital.Patients> responseObserver) {
        checkPermission(request.getId());
        List<Hospital.Patient> patients = Server.exams.stream().filter(e -> e.getDoctor().getIdentity().getId() == request.getId()).map(Hospital.MedicalExam::getPatient).collect(Collectors.toList());
        Hospital.Patients patients1 = Hospital.Patients.newBuilder().addAllPatients(patients).build();
        responseObserver.onNext(patients1);
        responseObserver.onCompleted();
    }

    @Override
    public void requestMedicalExamsForDoctor(Hospital.Request request, StreamObserver<Hospital.MedicalExams> responseObserver) {
        checkPermission(request.getId());
        List<Hospital.MedicalExam> exams = Server.exams.stream().filter(e -> e.getDoctor().getIdentity().getId() == request.getId()).collect(Collectors.toList());
        Hospital.MedicalExams medicalExams = Hospital.MedicalExams.newBuilder().addAllExams(exams).build();
        responseObserver.onNext(medicalExams);
        responseObserver.onCompleted();
    }

    @Override
    public void requestResultsInRange(Hospital.FilterByRangeRequest request, StreamObserver<Hospital.MedicalExams> responseObserver) {
        checkPermission(request.getId());
        List<Hospital.MedicalExam> medicalExams = Server.exams.stream().filter(e -> {
            double value = e.getResultsMap().get(request.getName()).getValue();
            return value >= request.getMinValue() && value <= request.getMaxValue();
        }).collect(Collectors.toList());
        Hospital.MedicalExams exams = Hospital.MedicalExams.newBuilder().addAllExams(medicalExams).build();
        responseObserver.onNext(exams);
        responseObserver.onCompleted();
    }

    private void checkPermission(long id) {
        if(Objects.isNull(Server.doctors.get(id))) {
            throw new StatusRuntimeException(Status.PERMISSION_DENIED);
        }
    }
}
