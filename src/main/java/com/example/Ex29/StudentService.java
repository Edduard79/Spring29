package com.example.Ex29;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public Student updateStatus(Long id, boolean isWorking){
        Student opStudent = studentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found!"));
        opStudent.setWorking(isWorking);

        return studentRepo.save(opStudent);
    }
}
