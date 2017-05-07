import sys

import grpc

import hospital_pb2
import hospital_pb2_grpc

channel = grpc.insecure_channel('localhost:12345')
publicStub = hospital_pb2_grpc.PublicServiceStub(channel)
doctorStub = hospital_pb2_grpc.DoctorServiceStub(channel)
labSub = hospital_pb2_grpc.LabServiceStub(channel)
patientStub = hospital_pb2_grpc.PatientServiceStub(channel)

id = int(raw_input("Enter your id: "))

req = hospital_pb2.Request(id=id)

while True:
    try:
        line = sys.stdin.readline()
        if line == 'doctors\n':
            response = publicStub.ListDoctors(req)
            print response
        elif line == 'labs\n':
            response = publicStub.ListLabs(req)
            print response
        elif line == 'registerPatient\n':
            first_name = raw_input("Enter your first name")
            last_name = raw_input("Enter your last name")
            regreq = hospital_pb2.RegisterRequest(first_name=first_name, last_name=last_name)
            response = publicStub.RegisterPatient(regreq)
            print response
        elif line == 'patient results\n':
            response = patientStub.RequestAllResults(req)
            for k in response:
                print k
        elif line == 'data\n':
            print publicStub.RequestPersonalData(req)
        elif line == 'lab results\n':
            response = labSub.RequestAllResultsForLab(req)
            for r in response:
                print r
        elif line == 'add result\n':
            doc = int(raw_input("Doctor id: "))
            pat = int(raw_input("Patient id: "))
            results = {}
            while True:
                line = raw_input('name value unit: ')
                if line == '':
                    break
                data = line.split(" ")
                name = data[0]
                val = float(data[1])
                unit = data[2]
                res = hospital_pb2.Result(value=val, unit=unit)
                results[name] = res
            addReq = hospital_pb2.AddExamRequest(doctor=doc, patient=pat, lab=id, results=results)
            print labSub.AddResults(addReq)
        elif line == 'patients\n':
            print doctorStub.RequestAllPatients(req)
        elif line == 'doctorPatients\n':
            print doctorStub.RequestPatientsForDoctor(req)
        elif line == 'doctorExams\n':
            response = doctorStub.RequestMedicalExamsForDoctor(req)
            for r in response:
                print r
        elif line == 'resultsInRange\n':
            name = raw_input("name: ")
            min = int(raw_input("min: "))
            max = int(raw_input("max: "))
            rangeReq = hospital_pb2.FilterByRangeRequest(name=name, minValue=min, maxValue=max)
            response = doctorStub.RequestResultsInRange(rangeReq)
            for r in response:
                print r
    except grpc._channel._Rendezvous as e:
        print e._state.code
    except Exception as e:
        print e

