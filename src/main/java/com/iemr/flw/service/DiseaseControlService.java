package com.iemr.flw.service;

import com.iemr.flw.dto.iemr.*;

public interface DiseaseControlService {
    public String save(DiseaseControlDTO diseaseControlDTO);
    public String saveMalaria(MalariaDTO diseaseControlDTO);
    public String saveKalaAzar(KalaAzarDTO diseaseControlDTO);
    public String saveAES(AesJeDTO diseaseControlDTO);
    public String saveFilaria(FilariaDTO diseaseControlDTO);
    public String saveLeprosy(LeprosyDTO diseaseControlDTO);
    public Object getAll(GetDiseaseRequestHandler getDiseaseRequestHandler);
}
