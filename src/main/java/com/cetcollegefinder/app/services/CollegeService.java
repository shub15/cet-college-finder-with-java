package com.cetcollegefinder.app.services;

import com.cetcollegefinder.app.dto.College;
import com.cetcollegefinder.app.dto.CollegeBranch;
import com.cetcollegefinder.app.dto.CutoffCategory;
import com.cetcollegefinder.app.repositories.CollegeBranchRepository;
import com.cetcollegefinder.app.repositories.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private CollegeBranchRepository collegeBranchRepository;

    public List<College> getAllColleges() {
        return collegeRepository.findAll();
    }

    public College getCollegeById(Long id) {
        return collegeRepository.findById(id).orElse(null);
    }

    public College saveCollege(College college) {
        return collegeRepository.save(college);
    }

    public void deleteCollege(Long id) {
        collegeRepository.deleteById(id);
    }

    // Operations for CollegeBranch
    public CollegeBranch saveCollegeBranch(Long collegeId, CollegeBranch collegeBranch) {
        College college = getCollegeById(collegeId);
        if (college != null) {
            collegeBranch.setCollege(college);
            return collegeBranchRepository.save(collegeBranch);
        }
        return null;
    }

    public CollegeBranch updateBranchCutoffs(Long collegeId, Long branchId, Map<String, Double> updatedCutoffs) {
    College college = collegeRepository.findById(collegeId)
            .orElseThrow(() -> new RuntimeException("College not found"));
    CollegeBranch branch = college.getBranches().stream()
            .filter(b -> b.getCollegeBranchId().equals(branchId))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Branch not found"));

    CutoffCategory cutoffCategories = branch.getCutoffCategories();

    // Update individual fields based on category names
    updatedCutoffs.forEach((categoryName, value) -> {
        switch (categoryName) {
            case "openRank":
                cutoffCategories.setOpenRank(value != null ? value.intValue() : null); // Expects Integer
                break;
            case "openPercentile":
                cutoffCategories.setOpenPercentile(value); // Expects Double
                break;
            case "tfwsRank":
                cutoffCategories.setTfwsRank(value != null ? value.intValue() : null); // Expects Integer
                break;
            case "tfwsPercentile":
                cutoffCategories.setTfwsPercentile(value); // Expects Double
                break;
            case "obcRank":
                cutoffCategories.setObcRank(value != null ? value.intValue() : null); // Expects Integer
                break;
            case "obcPercentile":
                cutoffCategories.setObcPercentile(value); // Expects Double
                break;
            case "miRank":
                cutoffCategories.setMiRank(value != null ? value.intValue() : null); // Expects Integer
                break;
            case "miPercentile":
                cutoffCategories.setMiPercentile(value); // Expects Double
                break;
            case "ewsRank":
                cutoffCategories.setEwsRank(value != null ? value.intValue() : null); // Expects Integer
                break;
            case "ewsPercentile":
                cutoffCategories.setEwsPercentile(value); // Expects Double
                break;
            default:
                throw new IllegalArgumentException("Unknown category: " + categoryName);
        }
    });

    collegeRepository.save(college);  // Save the updated college with modified branch cutoffs
    return branch;
}
     

    // public CollegeBranch updateCollegeBranch(Long collegeId, Long branchId, CollegeBranch updatedBranch) {
    //     College college = getCollegeById(collegeId);
    //     if (college != null) {
    //         CollegeBranch existingBranch = collegeBranchRepository.findById(branchId).orElse(null);
    //         if (existingBranch != null) {
    //             existingBranch.setBranch(updatedBranch.getBranch());
    //             existingBranch.setCutoffCategories(updatedBranch.getCutoffCategories());
    //             return collegeBranchRepository.save(existingBranch);
    //         }
    //     }
    //     return null;
    // }

    public void deleteCollegeBranch(Long collegeId, Long branchId) {
        College college = getCollegeById(collegeId);
        if (college != null) {
            collegeBranchRepository.deleteById(branchId);
        }
    }
}
