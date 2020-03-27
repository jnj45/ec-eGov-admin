# ec-eGov-admin
전자정부 프레임워크 관리콘솔 프로젝트.
전자정부 시스템 관리 기능을 별도 독립적인 어플리케이션을 개발하여 재사용 목적.
Spring boot, Spring Data JPA, Thymeleaf 기반.

## 환경설정
  * eGov 3.9 버전 개발자 환경 사용.
  * eclipse lombok 설정할 것 : java -jar lombok.jar
  * 로컬 DB는 ec-eGov프로젝트의 script로 eGov 테이블 생성.
  * 샘플테이블은 sample-table.sql 로 생성.
  
## TO-DO 리스트
  ### Front-end
    * 디자인 적용 : 전자구매솔루션 디자인 활용하여 적용.(템플릿 프레임워크 적용 고려: tiles3? sitemesh?)
    * grid 라이브러리 및 tree 라이브러리 적용. (jqgrid등)
  ### Back-end
    * 전자정부 시스템관리 메뉴 중 필수기능 구현.
      1. 보안 (net.ecbank.fwk.admin.sec 패키지) (각 entity,web,repository,service)
        -  롤관리, 권한관리(권한-롤맵핑), 사용자별 권한관리, 
      2. 시스템관리(net.ecbank.fwk.admin.sys 패키지)
        - 코드그룹-코드관리, 프로그램 관리, 메뉴관리, 권한별 메뉴관리
      3. 로그조회(net.ecbank.fwk.admin.log 패키지)
    
    * MyBatis 적용
     
      
