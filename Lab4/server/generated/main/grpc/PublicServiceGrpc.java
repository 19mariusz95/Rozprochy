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
public final class PublicServiceGrpc {

  private PublicServiceGrpc() {}

  public static final String SERVICE_NAME = "PublicService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Hospital.Request,
      Hospital.Doctors> METHOD_LIST_DOCTORS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "PublicService", "ListDoctors"),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.Request.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.Doctors.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Hospital.Request,
      Hospital.Labs> METHOD_LIST_LABS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "PublicService", "ListLabs"),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.Request.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.Labs.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Hospital.RegisterRequest,
      Hospital.Patient> METHOD_REGISTER_PATIENT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "PublicService", "RegisterPatient"),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.RegisterRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.Patient.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Hospital.Request,
      Hospital.Person> METHOD_REQUEST_PERSONAL_DATA =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "PublicService", "RequestPersonalData"),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.Request.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.Person.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PublicServiceStub newStub(io.grpc.Channel channel) {
    return new PublicServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PublicServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PublicServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static PublicServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PublicServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class PublicServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void listDoctors(Hospital.Request request,
        io.grpc.stub.StreamObserver<Hospital.Doctors> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LIST_DOCTORS, responseObserver);
    }

    /**
     */
    public void listLabs(Hospital.Request request,
        io.grpc.stub.StreamObserver<Hospital.Labs> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LIST_LABS, responseObserver);
    }

    /**
     */
    public void registerPatient(Hospital.RegisterRequest request,
        io.grpc.stub.StreamObserver<Hospital.Patient> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REGISTER_PATIENT, responseObserver);
    }

    /**
     */
    public void requestPersonalData(Hospital.Request request,
        io.grpc.stub.StreamObserver<Hospital.Person> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REQUEST_PERSONAL_DATA, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_LIST_DOCTORS,
            asyncUnaryCall(
              new MethodHandlers<
                Hospital.Request,
                Hospital.Doctors>(
                  this, METHODID_LIST_DOCTORS)))
          .addMethod(
            METHOD_LIST_LABS,
            asyncUnaryCall(
              new MethodHandlers<
                Hospital.Request,
                Hospital.Labs>(
                  this, METHODID_LIST_LABS)))
          .addMethod(
            METHOD_REGISTER_PATIENT,
            asyncUnaryCall(
              new MethodHandlers<
                Hospital.RegisterRequest,
                Hospital.Patient>(
                  this, METHODID_REGISTER_PATIENT)))
          .addMethod(
            METHOD_REQUEST_PERSONAL_DATA,
            asyncUnaryCall(
              new MethodHandlers<
                Hospital.Request,
                Hospital.Person>(
                  this, METHODID_REQUEST_PERSONAL_DATA)))
          .build();
    }
  }

  /**
   */
  public static final class PublicServiceStub extends io.grpc.stub.AbstractStub<PublicServiceStub> {
    private PublicServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PublicServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PublicServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PublicServiceStub(channel, callOptions);
    }

    /**
     */
    public void listDoctors(Hospital.Request request,
        io.grpc.stub.StreamObserver<Hospital.Doctors> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_LIST_DOCTORS, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listLabs(Hospital.Request request,
        io.grpc.stub.StreamObserver<Hospital.Labs> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_LIST_LABS, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void registerPatient(Hospital.RegisterRequest request,
        io.grpc.stub.StreamObserver<Hospital.Patient> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REGISTER_PATIENT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void requestPersonalData(Hospital.Request request,
        io.grpc.stub.StreamObserver<Hospital.Person> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REQUEST_PERSONAL_DATA, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PublicServiceBlockingStub extends io.grpc.stub.AbstractStub<PublicServiceBlockingStub> {
    private PublicServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PublicServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PublicServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PublicServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public Hospital.Doctors listDoctors(Hospital.Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_LIST_DOCTORS, getCallOptions(), request);
    }

    /**
     */
    public Hospital.Labs listLabs(Hospital.Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_LIST_LABS, getCallOptions(), request);
    }

    /**
     */
    public Hospital.Patient registerPatient(Hospital.RegisterRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REGISTER_PATIENT, getCallOptions(), request);
    }

    /**
     */
    public Hospital.Person requestPersonalData(Hospital.Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REQUEST_PERSONAL_DATA, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PublicServiceFutureStub extends io.grpc.stub.AbstractStub<PublicServiceFutureStub> {
    private PublicServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PublicServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PublicServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PublicServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Hospital.Doctors> listDoctors(
        Hospital.Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_LIST_DOCTORS, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Hospital.Labs> listLabs(
        Hospital.Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_LIST_LABS, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Hospital.Patient> registerPatient(
        Hospital.RegisterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REGISTER_PATIENT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Hospital.Person> requestPersonalData(
        Hospital.Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REQUEST_PERSONAL_DATA, getCallOptions()), request);
    }
  }

  private static final int METHODID_LIST_DOCTORS = 0;
  private static final int METHODID_LIST_LABS = 1;
  private static final int METHODID_REGISTER_PATIENT = 2;
  private static final int METHODID_REQUEST_PERSONAL_DATA = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PublicServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PublicServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LIST_DOCTORS:
          serviceImpl.listDoctors((Hospital.Request) request,
              (io.grpc.stub.StreamObserver<Hospital.Doctors>) responseObserver);
          break;
        case METHODID_LIST_LABS:
          serviceImpl.listLabs((Hospital.Request) request,
              (io.grpc.stub.StreamObserver<Hospital.Labs>) responseObserver);
          break;
        case METHODID_REGISTER_PATIENT:
          serviceImpl.registerPatient((Hospital.RegisterRequest) request,
              (io.grpc.stub.StreamObserver<Hospital.Patient>) responseObserver);
          break;
        case METHODID_REQUEST_PERSONAL_DATA:
          serviceImpl.requestPersonalData((Hospital.Request) request,
              (io.grpc.stub.StreamObserver<Hospital.Person>) responseObserver);
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

  private static final class PublicServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Hospital.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PublicServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PublicServiceDescriptorSupplier())
              .addMethod(METHOD_LIST_DOCTORS)
              .addMethod(METHOD_LIST_LABS)
              .addMethod(METHOD_REGISTER_PATIENT)
              .addMethod(METHOD_REQUEST_PERSONAL_DATA)
              .build();
        }
      }
    }
    return result;
  }
}
