package phoenix.Mymichef.data.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import phoenix.Mymichef.data.entity.ApiEntity;

import javax.persistence.Column;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiDTO {

    public String RCP_SEQ;

    public String RCP_NM;

    public String RCP_WAY2;

    public String RCP_PAT2;

    public String INFO_WGT;

    public String INFO_ENG;

    public String INFO_CAR;

    public String INFO_PRO;

    public String INFO_FAT;

    public String INFO_NA;

    public String HASH_TAG;

    public String ATT_FILE_NO_MAIN;

    public String ATT_FILE_NO_MK;

    public String RCP_PARTS_DTLS;

    public String MANUAL01;

    public String MANUAL_IMG01;

    public String MANUAL02;

    public String MANUAL_IMG02;

    public String MANUAL03;

    public String MANUAL_IMG03;

    public String MANUAL04;

    public String MANUAL_IMG04;

    public String MANUAL05;

    public String MANUAL_IMG05;

    public String MANUAL06;

    public String MANUAL_IMG06;

    public String MANUAL07;

    public String MANUAL_IMG07;

    public String MANUAL08;

    public String MANUAL_IMG08;

    public String MANUAL09;

    public String MANUAL_IMG09;

    public String MANUAL10;

    public String MANUAL_IMG10;

    public String MANUAL11;

    public String MANUAL_IMG11;

    public String MANUAL12;

    public String MANUAL_IMG12;

    public String MANUAL13;

    public String MANUAL_IMG13;

    public String MANUAL14;

    public String MANUAL_IMG14;

    public String MANUAL15;

    public String MANUAL_IMG15;

    public String MANUAL16;

    public String MANUAL_IMG16;

    public String MANUAL17;

    public String MANUAL_IMG17;

    public String MANUAL18;

    public String MANUAL_IMG18;

    public String MANUAL19;

    public String MANUAL_IMG19;

    public String MANUAL20;

    public String MANUAL_IMG20;

    public ApiEntity toEntity(){
        return ApiEntity.builder()
                .RCP_SEQ(RCP_SEQ)
                .RCP_NM(RCP_NM)
                .RCP_WAY2(RCP_WAY2)
                .RCP_PAT2(RCP_PAT2)
                .INFO_WGT(INFO_WGT)
                .INFO_ENG(INFO_ENG)
                .INFO_CAR(INFO_CAR)
                .INFO_PRO(INFO_PRO)
                .INFO_FAT(INFO_FAT)
                .INFO_NA(INFO_NA)
                .HASH_TAG(HASH_TAG)
                .ATT_FILE_NO_MAIN(ATT_FILE_NO_MAIN)
                .ATT_FILE_NO_MK(ATT_FILE_NO_MK)
                .RCP_PARTS_DTLS(RCP_PARTS_DTLS)
                .MANUAL01(MANUAL01)
                .MANUAL_IMG01(MANUAL_IMG01)
                .MANUAL02(MANUAL02)
                .MANUAL_IMG02(MANUAL_IMG02)
                .MANUAL03(MANUAL03)
                .MANUAL_IMG03(MANUAL_IMG03)
                .MANUAL04(MANUAL04)
                .MANUAL_IMG04(MANUAL_IMG04)
                .MANUAL05(MANUAL05)
                .MANUAL_IMG05(MANUAL_IMG05)
                .MANUAL06(MANUAL06)
                .MANUAL_IMG06(MANUAL_IMG06)
                .MANUAL07(MANUAL07)
                .MANUAL_IMG07(MANUAL_IMG07)
                .MANUAL08(MANUAL08)
                .MANUAL_IMG08(MANUAL_IMG08)
                .MANUAL09(MANUAL09)
                .MANUAL_IMG09(MANUAL_IMG09)
                .MANUAL10(MANUAL10)
                .MANUAL_IMG10(MANUAL_IMG10)
                .MANUAL11(MANUAL11)
                .MANUAL_IMG11(MANUAL_IMG11)
                .MANUAL12(MANUAL12)
                .MANUAL_IMG12(MANUAL_IMG12)
                .MANUAL13(MANUAL13)
                .MANUAL_IMG13(MANUAL_IMG13)
                .MANUAL14(MANUAL14)
                .MANUAL_IMG14(MANUAL_IMG14)
                .MANUAL15(MANUAL15)
                .MANUAL_IMG15(MANUAL_IMG15)
                .MANUAL16(MANUAL16)
                .MANUAL_IMG16(MANUAL_IMG16)
                .MANUAL17(MANUAL17)
                .MANUAL_IMG17(MANUAL_IMG17)
                .MANUAL18(MANUAL18)
                .MANUAL_IMG18(MANUAL_IMG18)
                .MANUAL19(MANUAL19)
                .MANUAL_IMG19(MANUAL_IMG19)
                .MANUAL20(MANUAL20)
                .MANUAL_IMG20(MANUAL_IMG20)
                .build();
    }
}
