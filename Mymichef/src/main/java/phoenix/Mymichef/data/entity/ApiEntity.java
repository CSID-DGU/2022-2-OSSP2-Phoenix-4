//package phoenix.Mymichef.data.entity;
//
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity(name = "apitbl")
//public class ApiEntity {
//    @Id
//    @Column(name = "RCP_SEQ")
//    public String RCP_SEQ;
//
//    @Column(name = "RCP_NM")
//    public String RCP_NM;
//
//    @Column(name = "RCP_WAY2")
//    public String RCP_WAY2;
//
//    @Column(name = "RCP_PAT2")
//    public String RCP_PAT2;
//
//    @Column(name = "INFO_WGT")
//    public String INFO_WGT;
//
//    @Column(name = "INFO_ENG")
//    public String INFO_ENG;
//
//    @Column(name = "INFO_CAR")
//    public String INFO_CAR;
//
//    @Column(name = "INFO_PRO")
//    public String INFO_PRO;
//
//    @Column(name = "INFO_FAT")
//    public String INFO_FAT;
//
//    @Column(name = "INFO_NA")
//    public String INFO_NA;
//
//    @Column(name = "HASH_TAG")
//    public String HASH_TAG;
//
//    @Column(name = "ATT_FILE_NO_MAIN")
//    public String ATT_FILE_NO_MAIN;
//
//    @Column(name = "ATT_FILE_NO_MK")
//    public String ATT_FILE_NO_MK;
//
//    @Column(name = "RCP_PARTS_DTLS")
//    public String RCP_PARTS_DTLS;
//
//    @Column(name = "MANUAL01")
//    public String MANUAL01;
//
//    @Column(name = "MANUAL_IMG01")
//    public String MANUAL_IMG01;
//
//    @Column(name = "MANUAL02")
//    public String MANUAL02;
//
//    @Column(name = "MANUAL_IMG02")
//    public String MANUAL_IMG02;
//
//    @Column(name = "MANUAL03")
//    public String MANUAL03;
//
//    @Column(name = "MANUAL_IMG03")
//    public String MANUAL_IMG03;
//
//    @Column(name = "MANUAL04")
//    public String MANUAL04;
//
//    @Column(name = "MANUAL_IMG04")
//    public String MANUAL_IMG04;
//
//    @Column(name = "MANUAL05")
//    public String MANUAL05;
//
//    @Column(name = "MANUAL_IMG05")
//    public String MANUAL_IMG05;
//
//    @Column(name = "MANUAL06")
//    public String MANUAL06;
//
//    @Column(name = "MANUAL_IMG06")
//    public String MANUAL_IMG06;
//
//    @Column(name = "MANUAL07")
//    public String MANUAL07;
//
//    @Column(name = "MANUAL_IMG07")
//    public String MANUAL_IMG07;
//
//    @Column(name = "MANUAL08")
//    public String MANUAL08;
//
//    @Column(name = "MANUAL_IMG08")
//    public String MANUAL_IMG08;
//
//    @Column(name = "MANUAL09")
//    public String MANUAL09;
//
//    @Column(name = "MANUAL_IMG09")
//    public String MANUAL_IMG09;
//
//    @Column(name = "MANUAL10")
//    public String MANUAL10;
//
//    @Column(name = "MANUAL_IMG10")
//    public String MANUAL_IMG10;
//
//    @Column(name = "MANUAL11")
//    public String MANUAL11;
//
//    @Column(name = "MANUAL_IMG11")
//    public String MANUAL_IMG11;
//
//    @Column(name = "MANUAL12")
//    public String MANUAL12;
//
//    @Column(name = "MANUAL_IMG12")
//    public String MANUAL_IMG12;
//
//    @Column(name = "MANUAL13")
//    public String MANUAL13;
//
//    @Column(name = "MANUAL_IMG13")
//    public String MANUAL_IMG13;
//
//    @Column(name = "MANUAL14")
//    public String MANUAL14;
//
//    @Column(name = "MANUAL_IMG14")
//    public String MANUAL_IMG14;
//
//    @Column(name = "MANUAL15")
//    public String MANUAL15;
//
//    @Column(name = "MANUAL_IMG15")
//    public String MANUAL_IMG15;
//
//    @Column(name = "MANUAL16")
//    public String MANUAL16;
//
//    @Column(name = "MANUAL_IMG16")
//    public String MANUAL_IMG16;
//
//    @Column(name = "MANUAL17")
//    public String MANUAL17;
//
//    @Column(name = "MANUAL_IMG17")
//    public String MANUAL_IMG17;
//
//    @Column(name = "MANUAL18")
//    public String MANUAL18;
//
//    @Column(name = "MANUAL_IMG18")
//    public String MANUAL_IMG18;
//
//    @Column(name = "MANUAL19")
//    public String MANUAL19;
//
//    @Column(name = "MANUAL_IMG19")
//    public String MANUAL_IMG19;
//
//    @Column(name = "MANUAL20")
//    public String MANUAL20;
//
//    @Column(name = "MANUAL_IMG20")
//    public String MANUAL_IMG20;
//
//
//    @Builder
//    public ApiEntity(String RCP_SEQ,
//                     String RCP_NM,
//                     String RCP_WAY2,
//                     String RCP_PAT2,
//                     String INFO_WGT,
//                     String INFO_ENG,
//                     String INFO_CAR,
//                     String INFO_PRO,
//                     String INFO_FAT,
//                     String INFO_NA,
//                     String HASH_TAG,
//                     String ATT_FILE_NO_MAIN,
//                     String ATT_FILE_NO_MK,
//                     String RCP_PARTS_DTLS,
//                     String MANUAL01,
//                     String MANUAL_IMG01,
//                     String MANUAL02,
//                     String MANUAL_IMG02,
//                     String MANUAL03,
//                     String MANUAL_IMG03,
//                     String MANUAL04,
//                     String MANUAL_IMG04,
//                     String MANUAL05,
//                     String MANUAL_IMG05,
//                     String MANUAL06,
//                     String MANUAL_IMG06,
//                     String MANUAL07,
//                     String MANUAL_IMG07,
//                     String MANUAL08,
//                     String MANUAL_IMG08,
//                     String MANUAL09,
//                     String MANUAL_IMG09,
//                     String MANUAL10,
//                     String MANUAL_IMG10,
//                     String MANUAL11,
//                     String MANUAL_IMG11,
//                     String MANUAL12,
//                     String MANUAL_IMG12,
//                     String MANUAL13,
//                     String MANUAL_IMG13,
//                     String MANUAL14,
//                     String MANUAL_IMG14,
//                     String MANUAL15,
//                     String MANUAL_IMG15,
//                     String MANUAL16,
//                     String MANUAL_IMG16,
//                     String MANUAL17,
//                     String MANUAL_IMG17,
//                     String MANUAL18,
//                     String MANUAL_IMG18,
//                     String MANUAL19,
//                     String MANUAL_IMG19,
//                     String MANUAL20,
//                     String MANUAL_IMG20){
//        this.RCP_SEQ = RCP_SEQ;
//        this.RCP_NM = RCP_NM;
//        this.RCP_WAY2 = RCP_WAY2;
//        this.RCP_PAT2 = RCP_PAT2;
//        this.INFO_WGT = INFO_WGT;
//        this.INFO_ENG = INFO_ENG;
//        this.INFO_CAR = INFO_CAR;
//        this.INFO_PRO = INFO_PRO;
//        this.INFO_FAT = INFO_FAT;
//        this.INFO_NA = INFO_NA;
//        this.HASH_TAG = HASH_TAG;
//        this.ATT_FILE_NO_MAIN = ATT_FILE_NO_MAIN;
//        this.ATT_FILE_NO_MK = ATT_FILE_NO_MK;
//        this.RCP_PARTS_DTLS = RCP_PARTS_DTLS;
//        this.MANUAL01 = MANUAL01;
//        this.MANUAL_IMG01 = MANUAL_IMG01;
//        this.   MANUAL02 = MANUAL02;
//        this.MANUAL_IMG02 = MANUAL_IMG02;
//        this.   MANUAL03 = MANUAL03;
//        this.MANUAL_IMG03 = MANUAL_IMG03;
//        this.    MANUAL04 = MANUAL04;
//        this.MANUAL_IMG04 = MANUAL_IMG04;
//        this.    MANUAL05= MANUAL05;
//        this.MANUAL_IMG05 = MANUAL_IMG05;
//        this.   MANUAL06 = MANUAL06;
//        this.MANUAL_IMG06 = MANUAL_IMG06;
//        this.   MANUAL07 = MANUAL07;
//        this.MANUAL_IMG07 = MANUAL_IMG07;
//        this.   MANUAL08 =  MANUAL08;
//        this.MANUAL_IMG08 = MANUAL_IMG08;
//        this.   MANUAL09 = MANUAL09;
//        this.MANUAL_IMG09 = MANUAL_IMG09;
//        this.    MANUAL10 = MANUAL10;
//        this.MANUAL_IMG10 = MANUAL_IMG10;
//        this.   MANUAL11 = MANUAL11;
//        this.MANUAL_IMG11 = MANUAL_IMG11;
//        this.   MANUAL12 = MANUAL12;
//        this.MANUAL_IMG12 = MANUAL_IMG12;
//        this.    MANUAL13 = MANUAL13;
//        this.MANUAL_IMG13 = MANUAL_IMG13;
//        this.    MANUAL14 = MANUAL14;
//        this.MANUAL_IMG14 =  MANUAL_IMG14;
//        this.    MANUAL15 = MANUAL15;
//        this.MANUAL_IMG15 = MANUAL_IMG15;
//        this.    MANUAL16 = MANUAL16;
//        this.MANUAL_IMG16 = MANUAL_IMG16;
//        this.    MANUAL17 = MANUAL17;
//        this.MANUAL_IMG17 = MANUAL_IMG17;
//        this.    MANUAL18 = MANUAL18;
//        this.MANUAL_IMG18 = MANUAL_IMG18;
//        this.    MANUAL19 = MANUAL19;
//        this.MANUAL_IMG19 = MANUAL_IMG19;
//        this.    MANUAL20 = MANUAL20;
//        this.MANUAL_IMG20 = MANUAL_IMG20;
//
//    }
//}
