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
public final class LabServiceGrpc {

  private LabServiceGrpc() {}

  public static final String SERVICE_NAME = "LabService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Hospital.AddExamRequest,
      Hospital.Response> METHOD_ADD_RESULTS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "LabService", "AddResults"),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.AddExamRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.Response.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Hospital.Request,
      Hospital.MedicalExam> METHOD_REQUEST_ALL_RESULTS_FOR_LAB =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          generateFullMethodName(
              "LabService", "RequestAllResultsForLab"),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.Request.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(Hospital.MedicalExam.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LabServiceStub newStub(io.grpc.Channel channel) {
    return new LabServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LabServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new LabServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static LabServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new LabServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class LabServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void addResults(Hospital.AddExamRequest request,
        io.grpc.stub.StreamObserver<Hospital.Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ADD_RESULTS, responseObserver);
    }

    /**
     */
    public void requestAllResultsForLab(Hospital.Request request,
        io.grpc.stub.StreamObserver<Hospital.MedicalExam> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REQUEST_ALL_RESULTS_FOR_LAB, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_ADD_RESULTS,
            asyncUnaryCall(
              new MethodHandlers<
                Hospital.AddExamRequest,
                Hospital.Response>(
                  this, METHODID_ADD_RESULTS)))
          .addMethod(
            METHOD_REQUEST_ALL_RESULTS_FOR_LAB,
            asyncServerStreamingCall(
              new MethodHandlers<
                Hospital.Request,
                Hospital.MedicalExam>(
                  this, METHODID_REQUEST_ALL_RESULTS_FOR_LAB)))
          .build();
    }
  }

  /**
   */
  public static final class LabServiceStub extends io.grpc.stub.AbstractStub<LabServiceStub> {
    private LabServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LabServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LabServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LabServiceStub(channel, callOptions);
    }

    /**
     */
    public void addResults(Hospital.AddExamRequest request,
        io.grpc.stub.StreamObserver<Hospital.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_RESULTS, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void requestAllResultsForLab(Hospital.Request request,
        io.grpc.stub.StreamObserver<Hospital.MedicalExam> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_REQUEST_ALL_RESULTS_FOR_LAB, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class LabServiceBlockingStub extends io.grpc.stub.AbstractStub<LabServiceBlockingStub> {
    private LabServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LabServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LabServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LabServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public Hospital.Response addResults(Hospital.AddExamRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ADD_RESULTS, getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<Hospital.MedicalExam> requestAllResultsForLab(
        Hospital.Request request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_REQUEST_ALL_RESULTS_FOR_LAB, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LabServiceFutureStub extends io.grpc.stub.AbstractStub<LabServiceFutureStub> {
    private LabServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LabServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LabServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LabServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Hospital.Response> addResults(
        Hospital.AddExamRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_RESULTS, getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_RESULTS = 0;
  private static final int METHODID_REQUEST_ALL_RESULTS_FOR_LAB = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LabServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LabServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_RESULTS:
          serviceImpl.addResults((Hospital.AddExamRequest) request,
              (io.grpc.stub.StreamObserver<Hospital.Response>) responseObserver);
          break;
        case METHODID_REQUEST_ALL_RESULTS_FOR_LAB:
          serviceImpl.requestAllResultsForLab((Hospital.Request) request,
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

  private static final class LabServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Hospital.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (LabServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LabServiceDescriptorSupplier())
              .addMethod(METHOD_ADD_RESULTS)
              .addMethod(METHOD_REQUEST_ALL_RESULTS_FOR_LAB)
              .build();
        }
      }
    }
    return result;
  }
}
