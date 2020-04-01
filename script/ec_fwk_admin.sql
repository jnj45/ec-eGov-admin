--------------------------------------------------------
--  DDL for Table EA_SYS_PROPS
--------------------------------------------------------

  CREATE TABLE "EA_SYS_PROPS" 
   (	"PROP_ID" NUMBER, 
	"PROP_KEY" VARCHAR2(100 BYTE), 
	"PROP_VAL" VARCHAR2(200 BYTE), 
	"PROP_DESC" VARCHAR2(400 BYTE), 
	"FRST_REGIST_PNTTM" DATE, 
	"FRST_REGISTER_ID" VARCHAR2(20 BYTE), 
	"LAST_UPDT_PNTTM" DATE, 
	"LAST_UPDUSR_ID" VARCHAR2(20 BYTE), 
	"USE_YN" VARCHAR2(1 BYTE) DEFAULT 'Y'
   ) ;

   COMMENT ON COLUMN "EA_SYS_PROPS"."PROP_ID" IS '프로퍼티ID';
   COMMENT ON COLUMN "EA_SYS_PROPS"."PROP_KEY" IS '프로퍼티KEY';
   COMMENT ON COLUMN "EA_SYS_PROPS"."PROP_VAL" IS '프로퍼티값';
   COMMENT ON COLUMN "EA_SYS_PROPS"."PROP_DESC" IS '프로퍼티설명';
   COMMENT ON COLUMN "EA_SYS_PROPS"."FRST_REGIST_PNTTM" IS '최초등록시점';
   COMMENT ON COLUMN "EA_SYS_PROPS"."FRST_REGISTER_ID" IS '최초등록자ID';
   COMMENT ON COLUMN "EA_SYS_PROPS"."LAST_UPDT_PNTTM" IS '최종수정시점';
   COMMENT ON COLUMN "EA_SYS_PROPS"."LAST_UPDUSR_ID" IS '최종수정자ID';
   COMMENT ON COLUMN "EA_SYS_PROPS"."USE_YN" IS '사용여부YN';
   COMMENT ON TABLE "EA_SYS_PROPS"  IS '프레임워크관리자콘솔_시스템프로퍼티';
--------------------------------------------------------
--  DDL for Index ET_SYS_PROPS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ET_SYS_PROPS_PK" ON "EA_SYS_PROPS" ("PROP_ID") 
  ;
--------------------------------------------------------
--  DDL for Index EF_SYS_PROPS_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "EF_SYS_PROPS_UK1" ON "EA_SYS_PROPS" ("PROP_KEY") 
  ;
--------------------------------------------------------
--  Constraints for Table EA_SYS_PROPS
--------------------------------------------------------

  ALTER TABLE "EA_SYS_PROPS" ADD CONSTRAINT "EF_SYS_PROPS_UK1" UNIQUE ("PROP_KEY") ENABLE;
  ALTER TABLE "EA_SYS_PROPS" ADD CONSTRAINT "ET_SYS_PROPS_PK" PRIMARY KEY ("PROP_ID") ENABLE;
  ALTER TABLE "EA_SYS_PROPS" MODIFY ("PROP_VAL" NOT NULL ENABLE);
  ALTER TABLE "EA_SYS_PROPS" MODIFY ("PROP_KEY" NOT NULL ENABLE);
  ALTER TABLE "EA_SYS_PROPS" MODIFY ("PROP_ID" NOT NULL ENABLE);
