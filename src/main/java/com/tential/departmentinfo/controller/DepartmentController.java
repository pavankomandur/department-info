package com.tential.departmentinfo.controller;

import com.tential.departmentinfo.entity.Department;
import com.tential.departmentinfo.exception.DepartmentNotFoundException;
import com.tential.departmentinfo.exception.InvalidDepartmentException;
import com.tential.departmentinfo.model.DepartmentInfo;
import com.tential.departmentinfo.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository repository;
    static final Logger log = LoggerFactory.getLogger(DepartmentController.class);
    /*
        Handled Both Generic Exception as well as Customized Exception with Custom Messages in ControllerAdvice
     */
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Department> findById(@PathVariable long id) throws Exception{
        if (!repository.findById(id).isPresent()) throw new DepartmentNotFoundException();
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<DepartmentInfo> findAll() throws Exception{
        DepartmentInfo deptInfo=new DepartmentInfo();
        deptInfo.setDeptList(repository.findAll());
        return new ResponseEntity<DepartmentInfo>(deptInfo, HttpStatus.OK);
    }

    @PostMapping
    public Department create(@RequestBody Department deptInfo) throws Exception{
    /*
    Handled Both Generic Exception as well as Customized Exception with Custom Messages in ControllerAdvice
     */
            if (deptInfo!=null && deptInfo.getDepartment_name() != null && deptInfo.getDepartment_count() > 0)
                return repository.save(deptInfo);
            else
                throw new InvalidDepartmentException();

    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Department> update(@PathVariable("id") long id,
                                             @RequestBody Department department) throws Exception{

        if (!repository.findById(id).isPresent()) throw new DepartmentNotFoundException();
        return repository.findById(id)
                .map(record -> {
                    record.setDepartment_name(department.getDepartment_name());
                    record.setDepartment_count(department.getDepartment_count());
                    Department updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id) throws Exception{
        if (!repository.findById(id).isPresent()) throw new DepartmentNotFoundException();
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
