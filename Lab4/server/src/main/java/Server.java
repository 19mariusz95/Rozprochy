import io.grpc.ServerBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

public class Server {

    private static final int PORT = 12345;

    public static final Map<Long, Hospital.Patient> patients = new HashMap<>();
    public static Map<Long, Hospital.Lab> labs = new HashMap<>();
    public static Map<Long, Hospital.Doctor> doctors = new HashMap<>();
    public static final Set<Hospital.MedicalExam> exams = new HashSet<>();
    private static int personId = 1;
    private static List<String> names = new ArrayList<>();
    private static List<String> surNames = new ArrayList<>();
    private static Random random = new Random();
    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {
        prepareData();
        io.grpc.Server server = ServerBuilder.forPort(PORT)
                .addService(new DoctorServiceImplBaseImpl())
                .addService(new LabServiceImplBaseImpl())
                .addService(new PatientServiceImplBaseImpl())
                .addService(new PublicServiceImplBaseImpl())
                .build()
                .start();
        LOGGER.info("Server started");
        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
        server.awaitTermination();
    }

    private static void prepareData() throws FileNotFoundException {
        loadNamesAndSurnames();
        registerPatients();
        registerLabs();
        registerDoctors();
        registerFakeExams();
        names.clear();
        surNames.clear();
    }

    private static void registerFakeExams() {
        for (int i = 0; i < 50000; i++) {
            createFakeExam();
        }
    }

    private static void createFakeExam() {
        Hospital.Doctor doctor = randomDoctor();
        Hospital.Patient patient = randomPatient();
        Hospital.Lab lab = randomLab();
        Map<String, Hospital.Result> resultMap = new HashMap<>();
        resultMap.put("ALT", Hospital.Result.newBuilder().setValue(random.nextInt(35)+5).setUnit("U/I").build());
        resultMap.put("Cholesterol", Hospital.Result.newBuilder().setValue(random.nextInt(40)+160).setUnit("mg/dl").build());
        resultMap.put("Kreatynina", Hospital.Result.newBuilder().setValue(random.nextDouble()).setUnit("md/dl").build());
        Hospital.MedicalExam medicalExam = Hospital.MedicalExam.newBuilder()
                .setDoctor(doctor)
                .setPatient(patient)
                .setLab(lab)
                .setTime(LocalDateTime.now().toString())
                .putAllResults(resultMap)
                .build();
        exams.add(medicalExam);
    }

    private static Hospital.Lab randomLab() {
        return new ArrayList<>(labs.values()).get(random.nextInt(labs.size()));
    }

    private static Hospital.Patient randomPatient() {
        return new ArrayList<>(patients.values()).get(random.nextInt(patients.size()));
    }

    private static Hospital.Doctor randomDoctor() {
        return new ArrayList<>(doctors.values()).get(random.nextInt(doctors.size()));
    }

    private static void loadNamesAndSurnames() throws FileNotFoundException {
        Scanner scanner = new Scanner(Server.class.getResourceAsStream("names.txt"));
        while(scanner.hasNextLine()) {
            names.add(scanner.nextLine());
        }
        scanner = new Scanner(Server.class.getResourceAsStream("surnames.txt"));
        while(scanner.hasNextLine()) {
            surNames.add(scanner.nextLine());
        }
    }

    private static void registerDoctors() {
        for (int i = 0; i < 500; i++) {
            Hospital.Doctor doctor = Hospital.Doctor.newBuilder().setPerson(registerFakePerson()).build();
            doctors.put(doctor.getPerson().getId(), doctor);
        }
    }

    private static void registerLabs() {
        for (int i = 0; i < 500; i++) {
            Hospital.Lab lab = Hospital.Lab.newBuilder().setPerson(registerFakePerson()).build();
            labs.put(lab.getPerson().getId(), lab);
        }
    }

    private static void registerPatients() {
        for (int i = 0; i < 4000; i++) {
            Hospital.Patient patient = Hospital.Patient.newBuilder().setPerson(registerFakePerson()).build();
            patients.put(patient.getPerson().getId(), patient);
        }
    }

    private static Hospital.Person registerFakePerson() {
        return registerPerson(names.get(random.nextInt(names.size())), surNames.get(random.nextInt(surNames.size())));
    }

    public static Hospital.Person registerPerson(String firstName, String lastName) {
        return Hospital.Person.newBuilder().setId(personId++).setFirstName(firstName).setLastName(lastName).build();
    }
}
