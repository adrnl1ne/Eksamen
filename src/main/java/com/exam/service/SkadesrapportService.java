package com.exam.service;

import com.exam.model.entities.biler.Bil;
import com.exam.model.entities.biler.BilTilstand;
import com.exam.model.entities.biler.LejeAftale;
import com.exam.model.entities.biler.SkadesRapport;
import com.exam.repository.BilRepo;
import com.exam.repository.LejeaftaleRepo;
import com.exam.repository.SkadesRapportRepo;

public class SkadesrapportService {
    SkadesRapportRepo skadesRapportRepo = new SkadesRapportRepo();
    BilRepo bilRepo = new BilRepo();
    LejeaftaleRepo lejeaftaleRepo = new LejeaftaleRepo();

    public void createSkadeRapport(Bil bil, SkadesRapport skadesRapport/*, LejeAftale lejeAftale*/) {

        if (bilRepo.viewBil(bil.getStelnummer()).getTilstand().getInt() == 3) {
            skadesRapportRepo.createSkadesRapport(skadesRapport);

        }

        /* if (!lejeaftaleRepo.isLejeperiodeOverstået(lejeAftale) &&
                bilRepo.viewBil(bil.getStelnummer()).getTilstand().getInt() == 3) {
            lejeAftale.setSlutdato(bilRepo, lejeaftaleRepo);
            skadesRapportRepo.createSkadesRapport(skadesRapport);

        } else if (lejeaftaleRepo.isLejeperiodeOverstået(lejeAftale) &&
                bilRepo.viewBil(bil.getStelnummer()).getTilstand().getInt() == 3) {
            skadesRapportRepo.createSkadesRapport(skadesRapport);

        }*/
    }

}
