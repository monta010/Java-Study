package GUi;

//자바 AWT 계산기

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;


public class Calculators extends Frame implements ActionListener  {
    private static final String String = null;
	public TextField input;  //TextField 변수 선언
    public Button bt; //버튼  bt 선언
    public boolean state = true; //true 한자리 false면 한자리 
    public int op; //연산자 구분
    public double buffer; //숫자 저장
    public boolean opstate= true; // 연산자 버튼연속 상태(연속 상태가 True) // 연산자 + 값일경우
    public double saveop;
    ArrayList<ResultSave> rss;
    
class ResultSave{ //결과 백업값
    	public String _cals;
    	public double _result;  	
    	
    	public void save_result(double results){
    		_result = results;
    	}
    }


public static void main(String[] args) {
   Calculators cal = new Calculators();  
} 
Calculators(){ //이벤트 핸들러
    
    super("계산기"); // 이름지정 명령어
    Panel main = new Panel(); //패널  main 선언 - Input값
    main.setLayout(new BorderLayout()); // 기본 컴포넌트 틀 생성
    input = new TextField(); //TextField 값을 input에 대입
    main.add("North", input); //input을 북쪽 패널에 배치
    Panel p = new Panel(); //패널 P 선언
    p.setLayout(new GridLayout(5, 4)); // 테이블 4행 4열 셋팅 (p 패널)
    //패널 p 값을 4,4로 셋팅

    rss = new ArrayList<ResultSave>();
    //버튼 생성란
    String s = "789+456-123*0C=/";
    for(int i=0; i<s.length(); i++)
    {
        bt = new Button(s.substring(i, i+1)); //시작부터 끝까지
        p.add(bt);  //Panel 에다가 button(bt)값을 넣는다.
        bt.addActionListener(this); //버튼 이벤트 발생
    }
    String s1 = "저장";
    {
        bt = new Button(s1); //시작부터 끝까지
        p.add(bt);  //Panel 에다가 button(bt)값을 넣는다.
        bt.addActionListener(this); //버튼 이벤트 발생
    }
    String s2 = "로드";
    {
        bt = new Button(s2); //시작부터 끝까지
        p.add(bt);  //Panel 에다가 button(bt)값을 넣는다.
        bt.addActionListener(this); //버튼 이벤트 발생
    }
    String s3 = "연산가능";
    {
        bt = new Button(s3); //시작부터 끝까지
        p.add(bt);  //Panel 에다가 button(bt)값을 넣는다.
        bt.addActionListener(this); //버튼 이벤트 발생
        //Single.instance.start = true;
    }
    String s4 = "연산불가능";
    {
        bt = new Button(s4); //시작부터 끝까지
        p.add(bt);  //Panel 에다가 button(bt)값을 넣는다.
        bt.addActionListener(this); //버튼 이벤트 발생
        //Single.instance.start = false;
    }
    //버튼생성종료
    
    main.add("Center", p); //panel( p )를 중앙에 셋팅
    add(main);
    
    setSize(300 ,300); //사이즈
    show();  //화면 요청
       
    //종료버튼 -> 닫기 버튼 누를시 코드 실행
    addWindowListener(new WindowAdapter() {
    	//윈도우 이벤트  -> 닫기 버튼 클릭시 -> WindowClosing이 실행된다.
    	//Adpater 경우에는 아무런 내용 없는 클래스 -> 밑에 Closing 메소드를 사용하기 위하여
        public void windowClosing(WindowEvent we) { //종료 버튼 메소드 (닫기를 누르게 되면 실행)
            System.exit(0); //시스템 종류
        }
        });   
}
	//ActionListener의 상속받은 메소드 ActionPerformed 이다.
	public void actionPerformed(ActionEvent ae) { //ActionEvent 사용을 위해서 사용
		//일반 버튼 클릭시 이벤트가 실행된다.
		// 발생한 이벤트를  val값에 넣는다(String값으로 반환)
		String val = ae.getActionCommand();
		System.out.println(val + "val");
		if ( !(val.equals("저장") || val.equals("로드")||val.equals("연산가능")||val.equals("연산불가능")))
		{
			//문자열로 반환해야만 밑에서 나오는 C같은 문자나 0~9숫자를 비교할 수가 있다.
			if(val.equals("C")){ //C값을 받게 되면  ""출력 , 값 리셋
				input.setText(""); //if문이 참이면 공백 //setText는 텍스트 필드에서 텍스트를 전달
				buffer = 0;    // 버퍼에  0 값을 넣는다.
			}
			else if('0'<=val.charAt(0) && val.charAt(0) <= '9')
			{  //숫자 유무 확인 ( 0 ~ 9 )
				if(state){
					input.setText(val);

					System.out.println(val + "cal");
				}
				else{ 
					input.setText(input.getText()+val); //입력된값과 버튼
				}
				state = false; 
			}
			else {
				if(singleT.getInstance().start){
					Operation(val); //Operation 함수에 val 값을 넘겨줌
					//System.out.println(val + " op");
				}
				else{
					System.out.println(val + " xxxxxxxxx");
				}
			}
		}
		else
		{
			if(val.equals("저장")){
				saveop = Double.valueOf(input.getText()); //(new Double(input.getText())).doubleValue();
				
				rss.add(new ResultSave());
				rss.get(rss.size()-1).save_result(saveop);
				
			}
			else if(val.equals("로드")){
				for(int k = 0; k < rss.size(); k++){
					System.out.println(k + " = " + rss.get(k)._result);
				}
			}
			if(val.equals("연산가능")){
				//result();
				singleT.getInstance().start = true;
			}
			else if(val.equals("연산불가능")){
				System.out.println("연산 불가능합니다!");
				singleT.getInstance().start = false;
			}
		}
	}
	//연산 처리하는 메소드 Operation
	public void Operation(String val) {
	    if(val.equals("+")){
	        op = 1;  //Switch문 사용하기 위해서 사용
	        if(opstate) result();  //opstate는 연산자 버튼 연속 상태 확인!
	        opstate = true;	       
	    }else if(val.equals("-")){
	        op = 2;
	        if(opstate) result();
	        opstate = true;
	    }else if(val.equals("*")){
	        op = 3;
	        if(opstate) result();
	        opstate = true;
	    }else if(val.equals("/")){
	        op = 4;
	        if(opstate) result();
	        opstate = true;
	    }else {
	        result();
	    }
	    buffer = (new Double(input.getText())).doubleValue(); //Double형으로 만든다. -> 입력된 값을 버퍼에 저장
	    state = true; //값을 하나라고 표시 true를 통해서
	    }

	public void result() {  //result 계산식
        double result = 0; //result 초기화
        double in = (new Double(input.getText())).doubleValue(); //입력받은 값을 double형으로 변경
        switch(op){  //연산값을 따라 switch문을 사용하기 위해서 op변수를 사용함..
        case 1:
            result = buffer+in;      
            break;
        case 2:
            result = buffer-in;       
            break;
        case 3:
            result = buffer*in;       
            break;
        case 4:
            result = buffer/in;
            break;
        }
        input.setText(result+""); //값을 출력함
        opstate = false;  
	  }

}	
  
   


/*ResultSave save = new ResultSave();
 save.save_result(result); //결과 값을 ResultSave -> save_result에 저장
*/
    
/*
ResultSave rs1 = new ResultSave();
rs1.save_result("cals fx1", buffer);

ResultSave rs2 = new ResultSave();
rs2.save_result("cals fx2", in);

ResultSave rs3 = new ResultSave();
rs3.save_result("cals fx3", result);

System.out.println("처음값 : " + rs1._cals + "=" + rs1._result);
System.out.println("둘째값 : " + rs2._cals + "=" + rs2._result);
System.out.println("합계값 : " + rs3._cals + "=" + rs3._result);
*/



