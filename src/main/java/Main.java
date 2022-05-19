class Bus { //클래스 버스
    int maxPassenger; // 최대 승객수
    int passenger; // 현재 승객수
    int money; //버스로 번 돈
    double busNumber; // 버스 넘버
    int fuel; // 주유량
    int speed; // 현재 속도
    String state; // 버스 상태
    int price; // 버스 요금


    Bus() {
        this.maxPassenger = 30; //버스 최대 승객 30
        this.passenger = 0; //생성시 현재 승객 0
        this.busNumber = Math.random(); // 버스 번호 랜덤
        this.fuel = 100; // 주유량 100
        this.speed = 0; //현재 속도 0
        this.state = "운행"; // 운행 상태
        this.money = 0; // 번 돈 0
        this.price = 2000; // 요금 2000원
    }

    void run(int x) {
        if (fuel < 10) { //주유량이 10 미만이면
            state = "차고지행"; //운행 x
            System.out.println("주유가 필요합니다");// 메세지 출력

        } else { // 주유량 10 이상

            state = "운행"; //운행
            speed = x; // 속도를 x로
            System.out.println("버스 넘버: "+busNumber+"\n"+x+" 속도로 운행을 시작합니다"); // 운행 메세지

        }
    }

    void changeSpeed(int x) {

        if (fuel == 0) { //기름이 0이면 운행 종료
            state = "차고지행";
            System.out.println("기름이 떨어져 운행을 중지합니다");
            speed = 0;
        }
        speed += x; // 속도에 x 더해주기
        System.out.println("속도를"+x+"만큼 변경하여\n현재 속도: "+speed); //변경 된 현재 속도 출력

        if (fuel < 10) { //주유량이 10 미만이면 경고 메세지
            System.out.println("주유량을 확인해 주세요");
        }
    }

    void passengerUp(int x) {
        fuel--; // 승객이 탔다는건 정류장을 이동 했다고 인식하여 기름 줄임

        if (fuel == 0) {//기름 떨어지면 운행 종료
            state = "차고지행";
            System.out.println("기름이 떨어져 운행을 중지합니다");
            speed = 0;
        }
        if (x >= 0 && state == "운행") { // x가 0 이상이고 운행 중이라면
            for (int i = 0; i < x; i++) { //탑승 승객만큼 반복
                if (maxPassenger > passenger) { //최대 승객수를 넘어서지 않았다면
                    passenger++; //현재 승객수 +1
                    money += x*price; //버스로 번 돈 추가
                }else{//최대 승객수를 넘었으면
                    System.out.println("정원 초과로 탑승 하실 수 없습니다");
                }
            }
            System.out.println(x+"명 승차 하였습니다");//x가 0이상이니 승차
            System.out.println("현재 승객수 "+passenger+"명입니다");
        } else if (x<0 && state == "운행") {// 0미만 즉 승객이 줄었으면
            passenger += x; // 승객 줄이기(-이기에 그냥 더해줌)
            System.out.println(-x+"명 하차 하였습니다"); //하차 메세지
            System.out.println("현재 승객수 "+passenger+"명입니다");
        }
    }

    void stop() {
        if (passenger == 0) {//승객이 없어야 운행 종료
            state = "차고지행";
            speed = 0;
            System.out.println("운행을 종료합니다");
        }else { //승객이 남았다면
            System.out.println("승객이 남아있습니다");
        }
    }

    void fuelUp(int x) {//운행 중이 아닐때 기름 넣기
        if (state == "차고지행") {
            fuel += x;
            System.out.println("기름을 "+x+"만큼 넣어 현재 주유량: "+fuel);
        }else {
            System.out.println("운행 중에는 기름을 넣을 수 없습니다");
        }
    }


}

class Taxi{
    double taxiNumber; //택시 번호
    int fuel; // 주유량
    int speed;//현재 속도
    int destination; // 목적지
    int minInterval; // 기본 거리
    int goalInterval; //목적지까지 거리
    int minPrice; //기본 요금
    int intervalPrice; // 거리당 요금
    String state;// 운행 상태
    int location;//현재 위치
    int price; //최종 가격
    int money;// 택시로 번 돈

    public Taxi() { //아직 안받은 값은 0으로 초기화
        this.taxiNumber = Math.random(); // 택시 번호 랜덤
        this.fuel = 100; // 기름량 100
        this.destination = 0;
        this.speed = 0;
        this.minInterval = 1; //기본 거리 1로 설정
        this.goalInterval = 0;
        this.minPrice = 3500; // 기본 요금 3500으로 설정
        this.intervalPrice = 500; // 거리별 요금 500로 설정
        this.state = "일반"; // 상태 일반
        this.location = 0;
        this.price = 0;
        this.money = 0;
    }

    void changeSpeed(int x){
        speed += x; //x를 받아 속도에 더해줌

        System.out.println("속도 "+x+"만큼 변경하여\n현재 속도 "+speed+"입니다");
        //변경 된 속도 출력
    }
    void run(int x, int y) { //목적지와 속도를 받음
        if (state == "일반" && fuel >= 10) { //주유량이 10 이사이고 일반 상태라면
            destination = x; // x를 목적지로
            state = "운행 중"; //운행 중으로 변경
            speed = y; // 속도 y로 변경
            System.out.println("택시 번호: "+taxiNumber+"\n"+x + "까지" + y + "속도로 운행을 시작합니다");
        } else if (state == "운행 중") { // 운행 중이면 다른 사람 못 탐
            System.out.println("운행 중 탑승 불가");
        } else if (fuel < 10) { // 기름 부족하다면 운행 불가
            System.out.println("주유량을 확인해 주세요.");
        }
    }

    void arrive(){
        goalInterval = Math.abs(location-destination); // 목적지까지 거리 = 현재 위치-목적지의 절대값
        if(goalInterval-minInterval>0) { //기본 거리보다 더 갔다면
            price = minPrice + intervalPrice * (goalInterval - minInterval);// 거리당 요금 추가
        }else {
            price = minPrice; //기본 거리 안쪽이라면 기본 요금
        }
        location = destination; //이동한 위치로 현재 위치 변경
        System.out.println("현재위치 "+location+"\n결제 금액"+price+"원 입니다");
        money += price; //최종 금액만큼 돈을 범
        state = "일반"; // 다시 일반 상태로 변경
        fuel -= goalInterval; // 이동 거리만큼 연료 차감
        System.out.println("남은 연료:"+fuel);


    }
    void fuelUp(int x){ // 운행 중이 아니라면 기름 추가가
       if(state=="일반"){
            fuel += x;
            System.out.println("기름을 "+x+"만쿰 넣어 현재 주유량: "+fuel);
        }else {
            System.out.println("운행 중에는 기름을 넣을 수 없습니다");
        }
    }



}










public class Main {
    public static void main(String[] args) {

   Bus bus = new Bus();
   Taxi taxi = new Taxi();
   bus.run(1);
   bus.changeSpeed(3);
   bus.passengerUp(5);
   bus.passengerUp(-5);
   bus.stop();
   bus.fuelUp(2);

   System.out.println("\n----------------------------------------------\n");

   taxi.run(3,1);
   taxi.changeSpeed(5);
   taxi.arrive();
   taxi.fuelUp(3);



    }
}