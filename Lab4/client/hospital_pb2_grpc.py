# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
import grpc

import hospital_pb2 as hospital__pb2


class PatientServiceStub(object):

  def __init__(self, channel):
    """Constructor.

    Args:
      channel: A grpc.Channel.
    """
    self.RequestAllResults = channel.unary_stream(
        '/PatientService/RequestAllResults',
        request_serializer=hospital__pb2.Request.SerializeToString,
        response_deserializer=hospital__pb2.MedicalExam.FromString,
        )


class PatientServiceServicer(object):

  def RequestAllResults(self, request, context):
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')


def add_PatientServiceServicer_to_server(servicer, server):
  rpc_method_handlers = {
      'RequestAllResults': grpc.unary_stream_rpc_method_handler(
          servicer.RequestAllResults,
          request_deserializer=hospital__pb2.Request.FromString,
          response_serializer=hospital__pb2.MedicalExam.SerializeToString,
      ),
  }
  generic_handler = grpc.method_handlers_generic_handler(
      'PatientService', rpc_method_handlers)
  server.add_generic_rpc_handlers((generic_handler,))


class DoctorServiceStub(object):

  def __init__(self, channel):
    """Constructor.

    Args:
      channel: A grpc.Channel.
    """
    self.RequestAllPatients = channel.unary_unary(
        '/DoctorService/RequestAllPatients',
        request_serializer=hospital__pb2.Request.SerializeToString,
        response_deserializer=hospital__pb2.Patients.FromString,
        )
    self.RequestPatientsForDoctor = channel.unary_unary(
        '/DoctorService/RequestPatientsForDoctor',
        request_serializer=hospital__pb2.Request.SerializeToString,
        response_deserializer=hospital__pb2.Patients.FromString,
        )
    self.RequestMedicalExamsForDoctor = channel.unary_stream(
        '/DoctorService/RequestMedicalExamsForDoctor',
        request_serializer=hospital__pb2.Request.SerializeToString,
        response_deserializer=hospital__pb2.MedicalExam.FromString,
        )
    self.RequestResultsInRange = channel.unary_stream(
        '/DoctorService/RequestResultsInRange',
        request_serializer=hospital__pb2.FilterByRangeRequest.SerializeToString,
        response_deserializer=hospital__pb2.MedicalExam.FromString,
        )


class DoctorServiceServicer(object):

  def RequestAllPatients(self, request, context):
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def RequestPatientsForDoctor(self, request, context):
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def RequestMedicalExamsForDoctor(self, request, context):
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def RequestResultsInRange(self, request, context):
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')


def add_DoctorServiceServicer_to_server(servicer, server):
  rpc_method_handlers = {
      'RequestAllPatients': grpc.unary_unary_rpc_method_handler(
          servicer.RequestAllPatients,
          request_deserializer=hospital__pb2.Request.FromString,
          response_serializer=hospital__pb2.Patients.SerializeToString,
      ),
      'RequestPatientsForDoctor': grpc.unary_unary_rpc_method_handler(
          servicer.RequestPatientsForDoctor,
          request_deserializer=hospital__pb2.Request.FromString,
          response_serializer=hospital__pb2.Patients.SerializeToString,
      ),
      'RequestMedicalExamsForDoctor': grpc.unary_stream_rpc_method_handler(
          servicer.RequestMedicalExamsForDoctor,
          request_deserializer=hospital__pb2.Request.FromString,
          response_serializer=hospital__pb2.MedicalExam.SerializeToString,
      ),
      'RequestResultsInRange': grpc.unary_stream_rpc_method_handler(
          servicer.RequestResultsInRange,
          request_deserializer=hospital__pb2.FilterByRangeRequest.FromString,
          response_serializer=hospital__pb2.MedicalExam.SerializeToString,
      ),
  }
  generic_handler = grpc.method_handlers_generic_handler(
      'DoctorService', rpc_method_handlers)
  server.add_generic_rpc_handlers((generic_handler,))


class LabServiceStub(object):

  def __init__(self, channel):
    """Constructor.

    Args:
      channel: A grpc.Channel.
    """
    self.AddResults = channel.unary_unary(
        '/LabService/AddResults',
        request_serializer=hospital__pb2.AddExamRequest.SerializeToString,
        response_deserializer=hospital__pb2.Response.FromString,
        )
    self.RequestAllResultsForLab = channel.unary_stream(
        '/LabService/RequestAllResultsForLab',
        request_serializer=hospital__pb2.Request.SerializeToString,
        response_deserializer=hospital__pb2.MedicalExam.FromString,
        )


class LabServiceServicer(object):

  def AddResults(self, request, context):
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def RequestAllResultsForLab(self, request, context):
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')


def add_LabServiceServicer_to_server(servicer, server):
  rpc_method_handlers = {
      'AddResults': grpc.unary_unary_rpc_method_handler(
          servicer.AddResults,
          request_deserializer=hospital__pb2.AddExamRequest.FromString,
          response_serializer=hospital__pb2.Response.SerializeToString,
      ),
      'RequestAllResultsForLab': grpc.unary_stream_rpc_method_handler(
          servicer.RequestAllResultsForLab,
          request_deserializer=hospital__pb2.Request.FromString,
          response_serializer=hospital__pb2.MedicalExam.SerializeToString,
      ),
  }
  generic_handler = grpc.method_handlers_generic_handler(
      'LabService', rpc_method_handlers)
  server.add_generic_rpc_handlers((generic_handler,))


class PublicServiceStub(object):

  def __init__(self, channel):
    """Constructor.

    Args:
      channel: A grpc.Channel.
    """
    self.ListDoctors = channel.unary_unary(
        '/PublicService/ListDoctors',
        request_serializer=hospital__pb2.Request.SerializeToString,
        response_deserializer=hospital__pb2.Doctors.FromString,
        )
    self.ListLabs = channel.unary_unary(
        '/PublicService/ListLabs',
        request_serializer=hospital__pb2.Request.SerializeToString,
        response_deserializer=hospital__pb2.Labs.FromString,
        )
    self.RegisterPatient = channel.unary_unary(
        '/PublicService/RegisterPatient',
        request_serializer=hospital__pb2.RegisterRequest.SerializeToString,
        response_deserializer=hospital__pb2.Patient.FromString,
        )
    self.RequestPersonalData = channel.unary_unary(
        '/PublicService/RequestPersonalData',
        request_serializer=hospital__pb2.Request.SerializeToString,
        response_deserializer=hospital__pb2.Person.FromString,
        )


class PublicServiceServicer(object):

  def ListDoctors(self, request, context):
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def ListLabs(self, request, context):
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def RegisterPatient(self, request, context):
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')

  def RequestPersonalData(self, request, context):
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')


def add_PublicServiceServicer_to_server(servicer, server):
  rpc_method_handlers = {
      'ListDoctors': grpc.unary_unary_rpc_method_handler(
          servicer.ListDoctors,
          request_deserializer=hospital__pb2.Request.FromString,
          response_serializer=hospital__pb2.Doctors.SerializeToString,
      ),
      'ListLabs': grpc.unary_unary_rpc_method_handler(
          servicer.ListLabs,
          request_deserializer=hospital__pb2.Request.FromString,
          response_serializer=hospital__pb2.Labs.SerializeToString,
      ),
      'RegisterPatient': grpc.unary_unary_rpc_method_handler(
          servicer.RegisterPatient,
          request_deserializer=hospital__pb2.RegisterRequest.FromString,
          response_serializer=hospital__pb2.Patient.SerializeToString,
      ),
      'RequestPersonalData': grpc.unary_unary_rpc_method_handler(
          servicer.RequestPersonalData,
          request_deserializer=hospital__pb2.Request.FromString,
          response_serializer=hospital__pb2.Person.SerializeToString,
      ),
  }
  generic_handler = grpc.method_handlers_generic_handler(
      'PublicService', rpc_method_handlers)
  server.add_generic_rpc_handlers((generic_handler,))
