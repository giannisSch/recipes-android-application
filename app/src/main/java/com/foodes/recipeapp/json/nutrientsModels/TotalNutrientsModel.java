package com.foodes.recipeapp.json.nutrientsModels;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class TotalNutrientsModel implements Parcelable {
    EnercKcalModel ENERC_KCAL;
    FatModel FAT;
    FasatModel FASAT;
    FatrnModel FATRN;
    FamsModel FAMS;
    FapuModel FAPU;
    ChocdfModel CHOCDF;
    FibtgModel FIBTG;
    SugarModel SUGAR;
    ProcntModel PROCNT;
    CholeModel CHOLE;
    NaModel NA;
    CaModel CA;
    MgModel MG;
    KModel K;
    FeModel FE;
    ZnModel ZN;
    PModel P;
    VitaRaeModel VITA_RAE;
    VitcModel VITC;
    ThiaModel THIA;
    RibfModel RIBF;
    NiaModel NIA;
    Vitb6aModel VITB6A;
    FoldfeModel FOLDFE;
    FolfdModel FOLFD;
    FolacModel FOLAC;
    Vitb12Model VITB12;
    VitdModel VITD;
    TocphaModel TOCPHA;
    Vitk1Model VITK1;
    WaterModel WATER;

    protected TotalNutrientsModel(Parcel in) {
    }

    public static final Creator<TotalNutrientsModel> CREATOR = new Creator<TotalNutrientsModel>() {
        @Override
        public TotalNutrientsModel createFromParcel(Parcel in) {
            return new TotalNutrientsModel(in);
        }

        @Override
        public TotalNutrientsModel[] newArray(int size) {
            return new TotalNutrientsModel[size];
        }
    };

    public EnercKcalModel getENERC_KCAL() {
        return ENERC_KCAL;
    }

    public void setENERC_KCAL(EnercKcalModel ENERC_KCAL) {
        this.ENERC_KCAL = ENERC_KCAL;
    }

    public FatModel getFAT() {
        return FAT;
    }

    public void setFAT(FatModel FAT) {
        this.FAT = FAT;
    }

    public FasatModel getFASAT() {
        return FASAT;
    }

    public void setFASAT(FasatModel FASAT) {
        this.FASAT = FASAT;
    }

    public FatrnModel getFATRN() {
        return FATRN;
    }

    public void setFATRN(FatrnModel FATRN) {
        this.FATRN = FATRN;
    }

    public FamsModel getFAMS() {
        return FAMS;
    }

    public void setFAMS(FamsModel FAMS) {
        this.FAMS = FAMS;
    }

    public FapuModel getFAPU() {
        return FAPU;
    }

    public void setFAPU(FapuModel FAPU) {
        this.FAPU = FAPU;
    }

    public ChocdfModel getCHOCDF() {
        return CHOCDF;
    }

    public void setCHOCDF(ChocdfModel CHOCDF) {
        this.CHOCDF = CHOCDF;
    }

    public FibtgModel getFIBTG() {
        return FIBTG;
    }

    public void setFIBTG(FibtgModel FIBTG) {
        this.FIBTG = FIBTG;
    }

    public SugarModel getSUGAR() {
        return SUGAR;
    }

    public void setSUGAR(SugarModel SUGAR) {
        this.SUGAR = SUGAR;
    }

    public ProcntModel getPROCNT() {
        return PROCNT;
    }

    public void setPROCNT(ProcntModel PROCNT) {
        this.PROCNT = PROCNT;
    }

    public CholeModel getCHOLE() {
        return CHOLE;
    }

    public void setCHOLE(CholeModel CHOLE) {
        this.CHOLE = CHOLE;
    }

    public NaModel getNA() {
        return NA;
    }

    public void setNA(NaModel NA) {
        this.NA = NA;
    }

    public CaModel getCA() {
        return CA;
    }

    public void setCA(CaModel CA) {
        this.CA = CA;
    }

    public MgModel getMG() {
        return MG;
    }

    public void setMG(MgModel MG) {
        this.MG = MG;
    }

    public KModel getK() {
        return K;
    }

    public void setK(KModel k) {
        K = k;
    }

    public FeModel getFE() {
        return FE;
    }

    public void setFE(FeModel FE) {
        this.FE = FE;
    }

    public ZnModel getZN() {
        return ZN;
    }

    public void setZN(ZnModel ZN) {
        this.ZN = ZN;
    }

    public PModel getP() {
        return P;
    }

    public void setP(PModel p) {
        P = p;
    }

    public VitaRaeModel getVITA_RAE() {
        return VITA_RAE;
    }

    public void setVITA_RAE(VitaRaeModel VITA_RAE) {
        this.VITA_RAE = VITA_RAE;
    }

    public VitcModel getVITC() {
        return VITC;
    }

    public void setVITC(VitcModel VITC) {
        this.VITC = VITC;
    }

    public ThiaModel getTHIA() {
        return THIA;
    }

    public void setTHIA(ThiaModel THIA) {
        this.THIA = THIA;
    }

    public RibfModel getRIBF() {
        return RIBF;
    }

    public void setRIBF(RibfModel RIBF) {
        this.RIBF = RIBF;
    }

    public NiaModel getNIA() {
        return NIA;
    }

    public void setNIA(NiaModel NIA) {
        this.NIA = NIA;
    }

    public Vitb6aModel getVITB6A() {
        return VITB6A;
    }

    public void setVITB6A(Vitb6aModel VITB6A) {
        this.VITB6A = VITB6A;
    }

    public FoldfeModel getFOLDFE() {
        return FOLDFE;
    }

    public void setFOLDFE(FoldfeModel FOLDFE) {
        this.FOLDFE = FOLDFE;
    }

    public FolfdModel getFOLFD() {
        return FOLFD;
    }

    public void setFOLFD(FolfdModel FOLFD) {
        this.FOLFD = FOLFD;
    }

    public FolacModel getFOLAC() {
        return FOLAC;
    }

    public void setFOLAC(FolacModel FOLAC) {
        this.FOLAC = FOLAC;
    }

    public Vitb12Model getVITB12() {
        return VITB12;
    }

    public void setVITB12(Vitb12Model VITB12) {
        this.VITB12 = VITB12;
    }

    public VitdModel getVITD() {
        return VITD;
    }

    public void setVITD(VitdModel VITD) {
        this.VITD = VITD;
    }

    public TocphaModel getTOCPHA() {
        return TOCPHA;
    }

    public void setTOCPHA(TocphaModel TOCPHA) {
        this.TOCPHA = TOCPHA;
    }

    public Vitk1Model getVITK1() {
        return VITK1;
    }

    public void setVITK1(Vitk1Model VITK1) {
        this.VITK1 = VITK1;
    }

    public WaterModel getWATER() {
        return WATER;
    }

    public void setWATER(WaterModel WATER) {
        this.WATER = WATER;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
