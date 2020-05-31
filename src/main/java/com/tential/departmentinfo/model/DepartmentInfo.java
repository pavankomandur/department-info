package com.tential.departmentinfo.model;

import com.tential.departmentinfo.entity.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentInfo {

    public List<Department> deptList=new ArrayList<Department>();

    public List<Department> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<Department> deptList) {
        this.deptList = deptList;
    }
}
