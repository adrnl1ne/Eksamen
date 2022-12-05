package com.exam.utilities;

import com.exam.model.entities.biler.LejeAftale;
import com.exam.repository.BilRepo;

import java.util.List;

public class UdregnPrisPåUdlejet {

    private static BilRepo bilRepo = new BilRepo();

    private UdregnPrisPåUdlejet() {
    }

    public static double calculateSumForUdlejet() {
        List<LejeAftale> udlejetBiler = bilRepo.viewLejeaftelerPåUdlejetBiler();
        double sum = 0;
        for (LejeAftale aftale : udlejetBiler) {
            sum += aftale.calculatePrice();
        }
        return sum;
    }

}
