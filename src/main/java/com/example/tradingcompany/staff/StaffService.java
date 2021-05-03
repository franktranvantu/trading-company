package com.example.tradingcompany.staff;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StaffService {

  private final StaffRepository staffRepository;

  public StaffService(StaffRepository staffRepository) {
    this.staffRepository = staffRepository;
  }

  public List<Staff> getAllStaffs() {
    return staffRepository.findAll();
  }

  public Staff getStaffById(long id) {
    return staffRepository.findById(id).orElse(null);
  }

  public Staff createStaff(Staff staff) {
    return staffRepository.save(staff);
  }

  public Staff updateStaff(long id, Staff staff) {
    Staff existStaff = staffRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Staff with id does not exist")));
    if (Objects.nonNull(staff.getName()) && !Objects.equals(existStaff.getName(), staff.getName())) {
      Optional<Staff> staffByName = staffRepository.findStaffByName(staff.getName());
      if (staffByName.isPresent()) {
        throw new IllegalArgumentException("Staff already exists");
      }
      existStaff.setName(staff.getName());
    }
    return staffRepository.save(existStaff);
  }

  public void deleteStaff(long id) {
    Optional<Staff> staff = staffRepository.findById(id);
    if (!staff.isPresent()) {
      throw new IllegalArgumentException(String.format("Staff with id %s not exists", id));
    }
    try {
      staffRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new IllegalStateException(String.format("Staff %s is being used by others!", staff.get().getName()));
    }
  }
}
