### 프로토타입 - 위치기반 출석인증 시나리오 구현
* 지오펜스 api 사용 안하고 구글맵 api로 동일한 기능 가능하도록 함 -> 위도, 경도 값 넘겨서 출석 기능까지 됨 -> 위도, 경도 저장 테이블 백에서 만들어서 출석 시스템 하면 될듯
* 반경 범위 최소로 설정 가능
* 아직 완성은 아니고 대충 기능은 비슷하게 해둠
* 각자 구글맵 api 키 발급하시고 manifests -> AndroidManifest.xml 파일에 젤 아래쪽 android:value="" 에 각자 키 넣고 사용하시면 됩니다.
* Android SDK 에서 Google Play services 설치하셔야 사용가능
  
  ## 사용법
  1. 메인화면에서 운동 클릭
  2. 챌린지 생성 : 모든 정보 입력 + 헬스장 위치 선택에서 위치 로고 클릭하면 위치 설정으로 넘어감
     * 모든 정보 입력 안하면 생성 불가능 / 위치 설정하면 설정된 위도, 경도가 표시됨
  3. 헬스장 위치 선택 : 헬스장 위치를 구글 지도에 클릭하면 반경 표시됨 -> 위치설정 누르면 헬스장 위치 지정 / 출석 체크는 현재 사용자 위치가 반경 내에 있으면 출석 인증 됨. 반경 밖이라면 출석인증이 안되고 루틴 시작 페이지에서 운동 시작이 안되도록함 / 완료 누르면 챌린지 생성 페이지로 복귀
  4. 운동시작 : 출석 되면 넘어가는거 확인 하려고 대충 버튼만 만들어둠 (추후 수정) / 헬스장 위치 선택에서 출석 인증이 성공 했으면 운동 시작 가능 / 출석 인증 실패라면 운동 시작 불가능(페이지가 안넘어감)
  5. 오늘의 운동 루틴페이지로 이동
 
  메인 기능만 먼저 해둠 - 나머진 종강하고 수정

  
