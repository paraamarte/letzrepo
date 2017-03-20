package com.letz.utils.common.constant;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public interface Constants {
    public final static String COMMON_BRAND_MALL_CD = "01";
    public static final String MALL_CD = "01"; // 몰코드
    public static final String DEFAULT_SHOP_CD = "00237";
    /************************** 코드그룹 코드 ***************************************/
    /** 지역번호 코드 */
    public static final String GRP_CD_TEL_CD = "CM021";
    /** 휴대전화국번 코드 */
    public static final String GRP_CD_CELL_CD = "CM022";

    /** 은행코드 */
    public static final String GRP_CD_BANK_CD = "OD022";

    /************************** 공통 *************************************************/
    /** 안내 > 유형(CM007) */
    public static final String NOTICE_TYPE_C_고객공지사항 = "C";
    public static final String NOTICE_TYPE_F_FAQ = "F";
    public static final String NOTICE_TYPE_I_기타안내 = "I";
    public static final String NOTICE_TYPE_N_어드민공지사항 = "N";
    public static final String NOTICE_TYPE_T_약관 = "T";

    /** 안내 > 약관(CM008) */
    public static final String NOTICE_TERMS_T01_이용약관 = "T01";
    public static final String NOTICE_TERMS_T02_개인정보취급처리방침 = "T02";
    public static final String NOTICE_TERMS_T03_위치정보수집 = "T03";
    public static final String NOTICE_TERMS_T04_신세계인터내셔날온라인사이트이용약관 = "T04";
    public static final String NOTICE_TERMS_T05_SIVILLAGER통합멤버십이용약관 = "T05";
    public static final String NOTICE_TERMS_T06_개인정보수집및이용동의필수 = "T06";
    public static final String NOTICE_TERMS_T07_개인정보수집및이용동의선택 = "T07";
    public static final String NOTICE_TERMS_T08_개인정보취급업무위탁에대한동의 = "T08";
    public static final String NOTICE_TERMS_T09_개인정보제3자제공동의 = "T09";
    public static final String NOTICE_TERMS_T10_신세계인터내셔날온라인사이트이용약관 = "T10";
    public static final String NOTICE_TERMS_T11_비회원정보수집 = "T11";
    public static final String NOTICE_TERMS_T12_주문정보제3자제공동의 = "T12";

    /************************** 고객센터 *************************************************/
    public static final String CS_INQ_GBN_CS_고객응대 = "CS";

    /** CS 문의 채널(CM012) */
    public static final String CS_INQ_CHANNEL_PC_PC접수 = "PC";
    public static final String CS_INQ_CHANNEL_MO_MOBILE접수 = "MO";
    public static final String CS_INQ_CHANNEL_OF_OFFLINE접수 = "OF";

    /** CS 문의 상태(CM013) */
    public static final String CS_INQ_STAT_C_답면완료 = "C";
    public static final String CS_INQ_STAT_P_답면보류 = "P";
    public static final String CS_INQ_STAT_R_답면대기 = "R";

    /** CS 문의 구분(CM014) */
    public static final String CS_INQ_GBN_OL_1대1문의 = "OL";

    /** CS 문의 상품유형(CM023) */
    public static final String CS_INQ_PRODUCT_TYPE_OR_주문상품 = "OR";
    public static final String CS_INQ_PRODUCT_TYPE_CA_장바구니 = "CA";
    public static final String CS_INQ_PRODUCT_TYPE_PU_찜 = "PU";
    public static final String CS_INQ_PRODUCT_TYPE_PD_상품 = "PD";

    /************************** 회원 *************************************************/
    /** 수신 동의 유형 (MB009) */
    public static final String RECEIVE_AGREE_TYPE_EMAIL = "10";
    public static final String RECEIVE_AGREE_TYPE_SMS = "20";
    public static final String RECEIVE_AGREE_TYPE_PUSH = "30";
    public static final String RECEIVE_AGREE_TYPE_DM = "40";
    public static final String RECEIVE_AGREE_TYPE_TM = "50";
    public static final String RECEIVE_AGREE_TYPE_SIV_PUSH = "60";
    public static final String RECEIVE_AGREE_TYPE_EVENT = "999"; // 201701 복주머니 이벤트 임시활용

    /** 브랜드 클럽 (MB_BRND_CLB) */
    public static final String BRAND_CLUB_JAJU = "1";
    public static final String BRAND_CLUB_BEAUTY = "2";

    /** 회원 가입 시, 약관 동의 유형 */
    public static final String AGRM_AGREE_TYPE_SIMALL_SVC = "10"; // 신세계인터내셔날 웹사이트 이용약관(필수)
    public static final String AGRM_AGREE_TYPE_MEMBER_SVC = "20"; // 신세계인터내셔날 통합회원 이용약관(필수)
    public static final String AGRM_AGREE_TYPE_USE = "30"; // 개인정보 수집 및 이용약관(필수)
    public static final String AGRM_AGREE_TYPE_ENTRUST = "50"; // 개인정보 수집 및 이용약관(선택) - 개인정보 취급 위탁
    public static final String AGRM_AGREE_TYPE_THIRDPARTY = "60"; // 개인정보 제3자 제공동의 (선택)

    /************************** 전시 *************************************************/
    public static final String DISPLAY_MAIN_HALL_SESSION_ID = "MAINHALL";
    public static final String DISPLAY_MAIN_HALL_KEY_SESSION_ID = "MAINHALLKEY";
    public static final String DISPLAY_GEN_GNB_SESSION_ID = "GENGNBVIEWYN";

    /** 메인관코드 */
    public static final String MAIN_HALL_CD_FA = "FA"; // fashion
    public static final String MAIN_HALL_CD_BE = "BE"; // beauty
    public static final String MAIN_HALL_CD_LI = "LI"; // living

    /** 전시연결 유형코드 (PD016) */
    public static final String DISPLAY_TYPE_NOT_LINK = "X"; // 연결없음
    public static final String DISPLAY_TYPE_SPECIAL = "S"; // 기획전 연결
    public static final String DISPLAY_TYPE_TEMPLATE = "T"; // 템플릿 연결
    public static final String DISPLAY_TYPE_URL_MOVE = "U"; // URL 이동

    /** 채널구분(PD012 / PD020) */
    public static final String ACCESS_POINT_CHANNEL_PC = "P"; // PC
    public static final String ACCESS_POINT_CHANNEL_MOBILE = "M"; // Mobile
    public static final String ACCESS_POINT_CHANNEL_APP = "AP"; // APP

    /** 전시카테고리 상품 리스트 페이징 유형 */
    public static final String DISPLAY_PRODUCT_SCRLL_PAGING = "S"; // 스크롤 페이징 방식
    public static final String DISPLAY_PRODUCT_DIVIDE_PAGING = "D"; // 일반 페이징

    public static final String FLAG_YES = "Y";
    public static final String FLAG_NO = "N";

    /** 이미지 파일 타입 */
    public static final String IMAGE_FILE_TYPE_PNG = "png";
    public static final String IMAGE_FILE_TYPE_JPEG = "jpeg";
    public static final String IMAGE_FILE_TYPE_JPG = "jpg";
    public static final String IMAGE_FILE_TYPE_BMP = "bmp";
    public static final String IMAGE_FILE_TYPE_GIF = "gif";

    /** 성별코드 */
    public static final String GENDER_SECTION_FEMALE = "F"; // 여성
    public static final String GENDER_SECTION_MALE = "M"; // 남성

    /** 매거진 속성 유형 */
    public static final String MAGAZINE_ATTR_GRP_SEASON = "M001"; // season
    public static final String MAGAZINE_ATTR_GRP_BRAND = "M002"; // brand
    public static final String MAGAZINE_ATTR_GRP_DESIGNER = "M003"; // designer
    public static final String MAGAZINE_ATTR_GRP_STORE = "M004"; // store
    public static final String MAGAZINE_ATTR_GRP_COLUMN = "M005"; // column

    /** 템플릿상세유형 */
    public static final String TMPL_DTL_TP_GE = "GE"; // 일반
    public static final String TMPL_DTL_TP_FA = "FA"; // 패션
    public static final String TMPL_DTL_TP_BE = "BE"; // 뷰티
    public static final String TMPL_DTL_TP_LO = "LO"; // 룩북

    /** 연관 매거진 자동 설정 여부 (대상 상품 자동 배정 여부) */
    public static final String TGT_PRODUCT_AUTO_ASGN_SCT_AUTO = "A";
    public static final String TGT_PRODUCT_AUTO_ASGN_SCT_MANUAL = "M";

    /************************** 상품 *************************************************/
    /** 상품유형(PD005) */
    public static final String PRODUCT_TP_P1_일반 = "P1";
    public static final String PRODUCT_TP_P2G_콜렉션그룹 = "P2G";
    public static final String PRODUCT_TP_P2_콜렉션 = "P2";
    public static final String PRODUCT_TP_PD_세트할인 = "PD";
    public static final String PRODUCT_TP_PS_각인구성 = "PS";
    public static final String PRODUCT_TP_PG_선물구성 = "PG";
    public static final String PRODUCT_TP_PG_다중구매 = "PM";

    /** 상품 판매 상태(PD005) */
    public static final String PRODUCT_SLAE_STATE_WAIT = "W"; // 판매준비중
    public static final String PRODUCT_SLAE_STATE_ING = "I"; // 판매진행
    public static final String PRODUCT_SLAE_STATE_END = "E"; // 판매종료
    public static final String PRODUCT_SLAE_STATE_SOLD_OUT = "S"; // 품절
    public static final String PRODUCT_SLAE_STATE_RESERVE_ING = "RI"; // 예약판매중

    /** 상품 필터 그룹 번호 */
    public static final String PRODUCT_FILTER_OPTION_GRP_NO = "F003"; // 옵션
    public static final String PRODUCT_FILTER_CHARACTER_GRP_NO = "F004"; // 특성

    /** 1:1문의 답변 상태 */
    public static final String PRODUCT_QUESTION_WAIT = "W"; // 답변대기
    public static final String PRODUCT_QUESTION_HOLD = "D"; // 답변보류
    public static final String PRODUCT_QUESTION_COMPLETE = "C"; // 답변완료

    /** 상품 이미지 사이즈 */
    public static final String PRODUCT_IMAGE_SIZE_DEFAULT = "0";
    public static final String PRODUCT_IMAGE_SIZE_50x50 = "50";
    public static final String PRODUCT_IMAGE_SIZE_60x60 = "60";
    public static final String PRODUCT_IMAGE_SIZE_72x72 = "72";
    public static final String PRODUCT_IMAGE_SIZE_80x80 = "80";
    public static final String PRODUCT_IMAGE_SIZE_122x122 = "122";
    public static final String PRODUCT_IMAGE_SIZE_150x150 = "150";
    public static final String PRODUCT_IMAGE_SIZE_160x160 = "160";
    public static final String PRODUCT_IMAGE_SIZE_224x224 = "224";
    public static final String PRODUCT_IMAGE_SIZE_260x260 = "260";
    public static final String PRODUCT_IMAGE_SIZE_274x274 = "274";
    public static final String PRODUCT_IMAGE_SIZE_300x300 = "300";
    public static final String PRODUCT_IMAGE_SIZE_480x480 = "480";
    public static final String PRODUCT_IMAGE_SIZE_500x500 = "500";
    public static final String PRODUCT_IMAGE_SIZE_2000x2000 = "2000";

    /** 상품 후기 유형 */
    public static final String PRODUCT_REVIEW_T = "T"; // 일반상품후기
    public static final String PRODUCT_REVIEW_P = "P"; // 포토상품후기

    /** 상품세트유형(PD025) */
    public static final String PRODUCT_SET_TP_NOMAL = "P"; // 일반상품
    public static final String PRODUCT_SET_TP_STAMP = "S"; // 각인서비스
    public static final String PRODUCT_SET_TP_GIFT = "G"; // 선물포장

    /************************** 주문 *************************************************/
    /** 주문상품유형(OD023) */
    public static final String ORDER_PRODUCT_TYPE_01_일반 = "01";
    public static final String ORDER_PRODUCT_TYPE_02_패키지 = "02";
    public static final String ORDER_PRODUCT_TYPE_03_패키지구성상품 = "03";
    public static final String ORDER_PRODUCT_TYPE_04_상품사은품 = "04";
    public static final String ORDER_PRODUCT_TYPE_05_주문사은품 = "05";

    public static final List<String> LOGGING_PROFILES = Arrays.asList("LOC", "DEV", "STG", "LOCPRD");

    /** 기획전 - 이벤트 유형[사용안함] (PD031) [R] 상품후기 이벤트 [O] 주문금액별 사은품 지급 이벤트 [E] 응모/당첨 이벤트 */
    public static final String SPECIAL_EVENT_TYPE_REVIEW = "R"; // 상품후기 이벤트
    public static final String SPECIAL_EVENT_TYPE_ORDER_PAY_FREEBIE = "O"; // 주문금액별 사은품 지급 이벤트
    public static final String SPECIAL_EVENT_TYPE_ENTRY = "R"; // 응모/당첨 이벤트

    /** 기획전 - 이벤트 유형 (PD031) [신규추가] **/
    public static final String EVENT_TYPE_ENTRY = "E"; // 응모/당첨 이벤트
    public static final String EVENT_TYPE_INFO = "I"; // 안내형
    public static final String EVENT_TYPE_COMMENT = "C"; // 댓글형

    /** 이벤트/기획전 - 응모횟수 코드 (PD033) [신규추가] [10] 1일 1회 응모 [20] 이벤트 기준 1회 응모 [30] 응모제한 없음 **/
    public static final String EVENT_ENTRY_TYPE_ONCE_PER_DAY = "10"; // 1일 1회 응모
    public static final String EVENT_ENTRY_TYPE_ONCE = "20"; // 이벤트 기준 1회 응모
    public static final String EVENT_ENTRY_TYPE_NO_LIMIT = "30"; // 응모제한 없음

    /** 이벤트 응모 가능 등급 코드 (MB008) [P] Gold 등급 이상 [V] Platinum 등급 이상 [VV] Diamond 등급 이상 **/
    public static final String EVENT_MEMBER_GRADE_GOLD = "P"; // Gold 등급 이상
    public static final String EVENT_MEMBER_GRADE_PLATINUM = "V"; // Platinum 등급 이상
    public static final String EVENT_MEMBER_GRADE_DIAMOND = "VV"; // Diamond 등급 이상

    /** 이벤트 구분 코드 **/
    public static final String EVENT_SPS_SECTION_SPECIAL_MEMBER = "SM"; // 특정회원 대상 이벤트

    /** 회원 등급 코드 (MB008) [G] Silver [P] Gold [V] Platinum [VV] Diamond **/
    public static final String MEMBER_GRADE_SILVER = "G"; // Silver
    public static final String MEMBER_GRADE_GOLD = "P"; // Gold
    public static final String MEMBER_GRADE_PLATINUM = "V"; // Platinum
    public static final String MEMBER_GRADE_DIAMOND = "VV"; // Diamond

    /** TODO: 배송 정보 */
    public static final BigDecimal DELIVERY_PRICE = new BigDecimal(2500); // 배송금액
    public static final BigDecimal DELIVERY_PRICE_INFO = new BigDecimal(30000); // 배송비
    public static final String DELIVERY_PRICE_TXT = "2,500원"; // 배송금액
    public static final String DELIVERY_PRICE_INFO_TXT = "30,000원 이상 구매 시 무료배송"; // 배송비 문구
    public static final String DELIVERY_AREA_INFO_TXT = "전국(단, 제주도 및 기타 도시 지방은 별도 요금 부과)"; // 배송가능지역
    public static final String DELIVERY_DATE_INFO_TXT = "3일 이내"; // 배송기일

    /** 이벤트 당첨 상태 */
    public static final String EVENT_WIN_TYPE_WAIT = "WT"; // 대기
    public static final String EVENT_WIN_TYPE_WIN = "WN"; // 당첨
    public static final String EVENT_WIN_TYPE_WIN_CANCLE = "WC"; // 당첨취소

    /** 이메일 발송유형 템플릿 코드 (CM016) **/
    public static final String EMAIL_TEMPLATE_C01_품절안내 = "C01";
    public static final String EMAIL_TEMPLATE_C02_교환완료 = "C02";
    public static final String EMAIL_TEMPLATE_C03_반품완료 = "C03";
    public static final String EMAIL_TEMPLATE_D01_상품발송안내 = "D01";
    public static final String EMAIL_TEMPLATE_D02_배송완료안내 = "D02";
    public static final String EMAIL_TEMPLATE_M01_회원가입완료 = "S_MB04";
    public static final String EMAIL_TEMPLATE_M02_임시비밀번호발급 = "S_MB02";
    public static final String EMAIL_TEMPLATE_M03_등급승격안내 = "S_MB03";
    public static final String EMAIL_TEMPLATE_M04_휴면계정전환안내 = "S_MB01";
    public static final String EMAIL_TEMPLATE_M05_이메일수신거부결과안내 = "S_MB06";
    public static final String EMAIL_TEMPLATE_O01_주문완료안내 = "O01";
    public static final String EMAIL_TEMPLATE_O02_입금확인안내 = "O02";
    public static final String EMAIL_TEMPLATE_O03_주문취소안내 = "O03";
    public static final String EMAIL_TEMPLATE_P01_장바구니상품 = "P01";
    public static final String EMAIL_TEMPLATE_P02_재입고알림 = "P02";
    public static final String EMAIL_TEMPLATE_S01_문의답변 = "S_CS01";
    public static final String EMAIL_TEMPLATE_W01_쿠폰발급 = "W01";
    public static final String EMAIL_TEMPLATE_W02_포인트소멸 = "S_BE01";
    public static final String EMAIL_TEMPLATE_W03_쿠폰유효기간만료 = "S_BE02";
    public static final String EMAIL_TEMPLATE_W04_발행즉시발급쿠폰발급 = "W04";

    /** 매장 유통 채널 구분 (CM038) **/
    public static final String SHOP_DSBT_CHANNEL_000_FSS = "000";
    public static final String SHOP_DSBT_CHANNEL_100_백화점 = "100";
    public static final String SHOP_DSBT_CHANNEL_200_대형마트 = "200";
    public static final String SHOP_DSBT_CHANNEL_300_대리점 = "300";
    public static final String SHOP_DSBT_CHANNEL_400_면세점 = "400";
    public static final String SHOP_DSBT_CHANNEL_500_프리미엄아울렛 = "500";
    public static final String SHOP_DSBT_CHANNEL_600_복합쇼핑몰 = "600";
    public static final String SHOP_DSBT_CHANNEL_700_패션전문점 = "700";
    public static final String SHOP_DSBT_CHANNEL_800_무점포 = "800";
    public static final String SHOP_DSBT_CHANNEL_900_기타 = "900";

    /** viewMode Session ID: Mobile에서만 사용. Footer > PC버전으로 보기 **/
    public static final String VIEW_MODE_SESSION_ID = "VIEW_MODE";
    public static final String VIEW_MODE_FO = "FO";
    public static final String VIEW_MODE_MO = "MO";

    /** APP을 통한 mo접근 시 app agent정보 세션설정 **/
    public static final String MOBILE_USER_AGENT_SESSION_ID = "MOBILE_USER_AGENT";
    public static final String MOBILE_USER_AGENT_JAJU = "JAJU_MOBILE";
    public static final String MOBILE_USER_AGENT_SIVILLAGE = "SIV_MOBILE";

    /* SMS 발신 이름 및 이메일 */
    public static final String SMS_SEND_NAME = "S.I.VILLAGE";
    public static final String SMS_SEND_CELLNO = "1644-4490";

    /* EMAIL 발신 이름 및 이메일 */
    public static final String EMAIL_SEND_NAME = "S.I.VILLAGE";
    public static final String EMAIL_SEND_EMAIL = "siv_cs@sikorea.co.kr";

    public static final String GROOBEE_SHOW_YN = "GROOBEE_SHOW_YN";

    /** 프로모션 유형 코드 (CC009) **/
    public static final String PROM_DTL_TP_CD_NPM_DISCOUNT_13 = "13";// 할인
    public static final String PROM_DTL_TP_CD_NPM_COMPOSITION_14 = "14";// 구성
}
