import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.3.0)",
    comments = "Source: hospital.proto")
public final class DoctorServiceGrpc {

  private DoctorServiceGrpc() {}

  public static final String SERVICE_NAME = "DoctorService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Hospital.Request,
      Hospital.Patients> METHOD_REQUEST_ALL_PATIENTS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "DoctorService", "RequestAllPatients"),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.Request.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.Patients.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Hospital.Request,
      Hospital.Patients> METHOD_REQUEST_PATIENTS_FOR_DOCTOR =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "DoctorService", "RequestPatientsForDoctor"),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.Request.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.Patients.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Hospital.Request,
      Hospital.MedicalExam> METHOD_REQUEST_MEDICAL_EXAMS_FOR_DOCTOR =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          generateFullMethodName(
              "DoctorService", "RequestMedicalExamsForDoctor"),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.Request.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.MedicalExam.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Hospital.FilterByRangeRequest,
      Hospital.MedicalExam> METHOD_REQUEST_RESULTS_IN_RANGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          generateFullMethodName(
              "DoctorService", "RequestResultsInRange"),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.FilterByRangeRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.MedicalExam.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DoctorServiceStub newStub(io.grpc.Channel channel) {
    return new DoctorServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DoctorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new DoctorServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static DoctorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new DoctorServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class DoctorServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void requestAllPatients(Hospital.Request request,
        io.grpc.stub.StreamObserver<Hospital.Patients> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REQUEST_ALL_PATIENTS, responseObserver);
    }

    /**
     */
    public void requestPatientsForDoctor(Hospital.Request request,
        io.grpc.stub.StreamObserver<Hospital.Patients> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REQUEST_PATIENTS_FOR_DOCTOR, responseObserver);
    }

    /**
     */
    public void requestMedicalExamsForDoctor(Hospital.Request request,
        io.grpc.stub.StreamObserver<Hospital.MedicalExam> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REQUEST_MEDICAL_EXAMS_FOR_DOCTOR, responseObserver);
    }

    /**
     */
    public void requestResultsInRange(Hospital.FilterByRangeRequest request,
        io.grpc.stub.StreamObserver<Hospital.MedicalExam> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REQUEST_RESULTS_IN_RANGE, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_REQUEST_ALL_PATIENTS,
            asyncUnaryCall(
              new MethodHandlers<
                Hospital.Request,
                Hospital.Patients>(
                  this, METHODID_REQUEST_ALL_PATIENTS)))
          .addMethod(
            METHOD_REQUEST_PATIENTS_FOR_DOCTOR,
            asyncUnaryCall(
              new MethodHandlers<
                Hospital.Request,
                Hospital.Patients>(
                  this, METHODID_REQUEST_PATIENTS_FOR_DOCTOR)))
          .addMethod(
            METHOD_REQUEST_MEDICAL_EXAMS_FOR_DOCTOR,
            asyncServerStreamingCall(
              new MethodHandlers<
                Hospital.Request,
                Hospital.MedicalExam>(
                  this, METHODID_REQUEST_MEDICAL_EXAMS_FOR_DOCTOR)))
          .addMethod(
            METHOD_REQUEST_RESULTS_IN_RANGE,
            asyncServerStreamingCall(
              new MethodHandlers<
                Hospital.FilterByRangeRequest,
                Hospital.MedicalExam>(
                  this, METHODID_REQUEST_RESULTS_IN_RANGE)))
          .build();
    }
  }

  /**
   */
  public static final class DoctorServiceStub extends io.grpc.stub.AbstractStub<DoctorServiceStub> {
    private DoctorServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DoctorServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DoctorServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DoctorServiceStub(channel, callOptions);
    }

    /**
     */
    public void requestAllPatients(Hospital.Request request,
        io.grpc.stub.StreamObserver<Hospital.Patients> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REQUEST_ALL_PATIENTS, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void requestPatientsForDoctor(Hospital.Request request,
        io.grpc.stub.StreamObserver<Hospital.Patients> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REQUEST_PATIENTS_FOR_DOCTOR, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void requestMedicalExamsForDoctor(Hospital.Request request,
        io.grpc.stub.StreamObserver<Hospital.MedicalExam> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_REQUEST_MEDICAL_EXAMS_FOR_DOCTOR, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void requestResultsInRange(Hospital.FilterByRangeRequest request,
        io.grpc.stub.StreamObserver<Hospital.MedicalExam> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_REQUEST_RESULTS_IN_RANGE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DoctorServiceBlockingStub extends io.grpc.stub.AbstractStub<DoctorServiceBlockingStub> {
    private DoctorServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DoctorServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DoctorServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DoctorServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public Hospital.Patients requestAllPatients(Hospital.Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REQUEST_ALL_PATIENTS, getCallOptions(), request);
    }

    /**
     */
    public Hospital.Patients requestPatientsForDoctor(Hospital.Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REQUEST_PATIENTS_FOR_DOCTOR, getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<Hospital.MedicalExam> requestMedicalExamsForDoctor(
        Hospital.Request request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_REQUEST_MEDICAL_EXAMS_FOR_DOCTOR, getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<Hospital.MedicalExam> requestResultsInRange(
        Hospital.FilterByRangeRequest request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_REQUEST_RESULTS_IN_RANGE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DoctorServiceFutureStub extends io.grpc.stub.AbstractStub<DoctorServiceFutureStub> {
    private DoctorServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DoctorServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DoctorServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DoctorServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Hospital.Patients> requestAllPatients(
        Hospital.Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REQUEST_ALL_PATIENTS, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Hospital.Patients> requestPatientsForDoctor(
        Hospital.Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REQUEST_PATIENTS_FOR_DOCTOR, getCallOptions()), request);
    }
  }

  private static final int METHODID_REQUEST_ALL_PATIENTS = 0;
  private static final int METHODID_REQUEST_PATIENTS_FOR_DOCTOR = 1;
  private static final int METHODID_REQUEST_MEDICAL_EXAMS_FOR_DOCTOR = 2;
  private static final int METHODID_REQUEST_RESULTS_IN_RANGE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DoctorServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DoctorServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REQUEST_ALL_PATIENTS:
          serviceImpl.requestAllPatients((Hospital.Request) request,
              (io.grpc.stub.StreamObserver<Hospital.Patients>) responseObserver);
          break;
        case METHODID_REQUEST_PATIENTS_FOR_DOCTOR:
          serviceImpl.requestPatientsForDoctor((Hospital.Request) request,
              (io.grpc.stub.StreamObserver<Hospital.Patients>) responseObserver);
          break;
        case METHODID_REQUEST_MEDICAL_EXAMS_FOR_DOCTOR:
          serviceImpl.requestMedicalExamsForDoctor((Hospital.Request) request,
              (io.grpc.stub.StreamObserver<Hospital.MedicalExam>) responseObserver);
          break;
        case METHODID_REQUEST_RESULTS_IN_RANGE:
          serviceImpl.requestResultsInRange((Hospital.FilterByRangeRequest) request,
              (io.grpc.stub.StreamObserver<Hospital.MedicalExam>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class DoctorServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Hospital.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DoctorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DoctorServiceDescriptorSupplier())
              .addMethod(METHOD_REQUEST_ALL_PATIENTS)
              .addMethod(METHOD_REQUEST_PATIENTS_FOR_DOCTOR)
              .addMethod(METHOD_REQUEST_MEDICAL_EXAMS_FOR_DOCTOR)
              .addMethod(METHOD_REQUEST_RESULTS_IN_RANGE)
              .build();
        }
      }
    }
    return result;
  }
}
