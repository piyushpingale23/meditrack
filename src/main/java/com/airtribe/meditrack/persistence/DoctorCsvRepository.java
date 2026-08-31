package com.airtribe.meditrack.persistence;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.enums.Specialization;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DoctorCsvRepository {

    private static final Path FILE = Path.of("data/doctors.csv");
    private final CsvFileManager fileManager = CsvFileManager.getInstance();

    public void saveAll(List<Doctor> doctors) {

        List<String> lines = new ArrayList<>();

        lines.add("id,name,age,mobile,specialization,consultationFee");

        for (Doctor doctor : doctors) {
            lines.add(doctor.getId() + "," +
                    doctor.getName() + "," +
                    doctor.getAge() + "," +
                    doctor.getMobile() + "," +
                    doctor.getSpecialization() + "," +
                    doctor.getConsultationFee()
            );
        }

        fileManager.write(FILE, lines);
    }

    public List<Doctor> loadAll() {

        List<String> lines = fileManager.read(FILE);

        List<Doctor> doctors = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {

            String[] data = lines.get(i).split(",");

            Doctor doctor = new Doctor(
                    Long.parseLong(data[0]),
                    data[1],
                    Integer.parseInt(data[2]),
                    data[3],
                    Specialization.valueOf(data[4]),
                    Double.parseDouble(data[5])
            );

            doctors.add(doctor);
        }

        return doctors;
    }
}