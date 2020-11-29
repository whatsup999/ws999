import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.Graphics;

public class MakeSeat extends JFrame{          
	public static void main(String[] args) {
           MakeSeat win = new MakeSeat();
           win.setTitle("좌석 신청(회원)");
           win.setSize(1100,800);
           win.setLocation(500,200);
           win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           Seatselection pn = new Seatselection();
           win.setContentPane(pn);      
           win.show();
        }  
}

 

class Seatselection extends JPanel implements ActionListener {
  int buttoncount=28; // 일반 좌석 버튼의 총 개수 
  int seatcount=28; //남은 좌석 수
  JLabel state1 = new JLabel("여기에 남은 좌석 수가 표시 됩니다");
  String[] btn_title = new String [buttoncount];   //좌석 번호 배열
  JButton[] btn = new JButton[btn_title.length];   //좌석 번호 배열의 길이만큼 버튼배열 생성
  private Image background=new ImageIcon("background.png").getImage();
  

      Seatselection() {         
         setLayout(null);


          for(int i=0; i<buttoncount;i++) {       //좌석 번호 생성
              btn_title[i] =Integer.toString(i);
          }

          for(int i=0; i<btn_title.length;i++) {
             btn[i] = new JButton(btn_title[i]);        // 좌석 버튼 28개 생성
             btn[i].addActionListener(this);
             btn[i].setBackground(new Color(206,242,121));
          }  
             JButton room1 = new JButton("room 1");        // 룸 좌석1 생성
             room1.addActionListener(this);
             room1.setBackground(new Color(206,242,121));
             JButton room2 = new JButton("room 2");        // 룸 좌석2 생성
             room2.addActionListener(this);
             room2.setBackground(new Color(206,242,121));
             JButton back = new JButton("뒤로 가기");          //뒤로 가기 버튼 생성
             back.addActionListener(this);
             back.setBackground(new Color(206,242,121));
             JButton membermanage = new JButton("회원 관리");   //회원 관리 버튼 생성
             membermanage.addActionListener(this);
             membermanage.setBackground(new Color(206,242,121));

          btn[0].setBounds(10,10,90,70);
          btn[1].setBounds(10,90,90,70);
          btn[2].setBounds(10,170,90,70);
          btn[3].setBounds(10,250,90,70);
          btn[4].setBounds(10,330,90,70);
          btn[5].setBounds(10,410,90,70);
          btn[6].setBounds(10,490,90,70);
          btn[7].setBounds(140,10,90,70);
          btn[8].setBounds(270,10,90,70);
          btn[9].setBounds(400,10,90,70);
          btn[10].setBounds(230,170,90,70);
          btn[11].setBounds(320,170,90,70);
          btn[12].setBounds(230,330,90,70);
          btn[13].setBounds(320,330,90,70);
          btn[14].setBounds(600,10,90,70);   //2번째 방 좌석 버튼 시작
          btn[15].setBounds(730,10,90,70);
          btn[16].setBounds(860,10,90,70);
          btn[17].setBounds(990,10,90,70);
          btn[18].setBounds(990,90,90,70);
          btn[19].setBounds(990,170,90,70);
          btn[20].setBounds(990,250,90,70);
          btn[21].setBounds(990,330,90,70);
          btn[22].setBounds(990,410,90,70);
          btn[23].setBounds(990,490,90,70);
          btn[24].setBounds(690,170,90,70);
          btn[25].setBounds(780,170,90,70);
          btn[26].setBounds(690,330,90,70);
          btn[27].setBounds(780,330,90,70);

          for(int i=0; i<btn_title.length;i++) {              // 1인 좌석 컨테이너에 올리기
              this.add(btn[i]);
          }  

          room1.setBounds(550,640,230,100);  //룸 좌석 버튼 생성
          this.add(room1);
          room2.setBounds(830,640,230,100);
          this.add(room2);
          back.setBounds(400,705,90,35);   //뒤로 가기 버튼
          this.add(back);
          membermanage.setBounds(300,705,90,35);  // 회원 관리 버튼
          this.add(membermanage);
           
          state1.setText("남아있는 일반 좌석 수는 "+seatcount+"석");  //남아있는 좌석 현황을 알려주는 
          state1.setBounds(310,550,200,80);
          this.add(state1);
          repaint();

       }  //생성자 끝
   public void paintComponent(Graphics g) {
      g.drawImage(background, 0, 0, getWidth(), getHeight(), null);//background를 그려줌
   }
   public void actionPerformed(ActionEvent ae) {
       String s = ae.getActionCommand();
       int i = Integer.parseInt(s);       // 버튼을 클릭했을 때 그 버튼의 좌석번호를 가져오는 변수 
       if( s == "뒤로 가기" ) {// 뒤로 가기 버튼 클릭 시 창 종료
       }
       else if( s == "회원 관리" )  {// 회원 관리 버튼 클릭 시 4번:회원 관리 창 띄우기
       }
       else { // 나머지 좌석 버튼이 눌렸을 때 담요, 독서대 등 대여 여부 확인 창 띄우기
           SeatCheck sc = new SeatCheck("확인창", this);
          sc.buttonnumber(i);
          sc.setTitle(i+"번 좌석 요청");
          sc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          sc.setSize(400,200);
          sc.show();
       }
   } // 메소드 끝
  public void enter(int i) {           //입실하면 해당 좌석 버튼 색 변경하는 메소드 !!!!!!!!
       btn[i].setBackground(Color.gray);
       btn[i].setOpaque(true);
       System.out.println(btn.length);
       System.out.println(i+"번 좌석 배경색 변경");
  } // enter 메소드 끝
} // Seatselection 클래스 끝 
class SeatCheck extends JFrame implements ActionListener {
      static int i;  // 좌석번호 받아오기 위한 변수
      JLabel jl; // 더 필요한 것이 있는지 묻는 레이블
      JCheckBox blanket, bookdesk;   // 담요, 독서대 체크박스 버튼
      JButton in, out, back;     // 확인, 퇴실, 뒤로 가기 버튼
      Seatselection ss;

      SeatCheck (String title, Seatselection ss) {
        this.ss = ss;
        Container ct = getContentPane();
        ct.setLayout(new BorderLayout(0,5));
        JPanel top = new JPanel();  //상단 패널
        JPanel bottom = new JPanel(); // 하단 패널
        
        jl = new JLabel("          더 필요한 것이 있으면 선택해주세요.");
       blanket = new JCheckBox("담요");
       bookdesk = new JCheckBox("독서대");
       in = new JButton("확인");
       out = new JButton("퇴실");
       back = new JButton("뒤로 가기");
                   
       in.addActionListener(this);
       out.addActionListener(this);       // 컴포넌트들 이벤트 생성
       back.addActionListener(this);

       top.add(jl);  top.add(blanket); top.add(bookdesk);     // 컴포넌트들 JPanel 위에 올리기
       bottom.add(in); bottom.add(out);  bottom.add(back);
   
       ct.add(top, BorderLayout.NORTH);
       ct.add(bottom, BorderLayout.SOUTH);      //컨테이너에 JPanel 올리기
      } //SeatCheck 생성자 끝
     public void actionPerformed(ActionEvent ae) {
       String s = ae.getActionCommand();
       if( s == "뒤로 가기" ) {// 뒤로 가기 버튼 클릭 시 창 종료
         dispose();
       }
       else if( s == "확인" )  {// 데이터베이스에 데이터 입력 후 버튼 색 변경
       ss.enter(i);
       System.out.println(ss.btn.length);
       dispose();
       }
       else { //퇴실 버튼이 눌렸을 때 
       dispose();    
       }
     }
    public void buttonnumber(int i) {  // 좌석 번호 받아오는 메소드
    SeatCheck.i=i;
    }

}  //클래스 끝
  
       
 
       
        
        
 
// * 에러 원인
// 1) MakeSeat에서 설정한 Seatselection 
// Seatselection pn = new Seatselection();
//
// 2) SeatCheck에서 설정한 SeatCheck
// Seatselection ss = new Seatselection();
//
// MakeSeat과 SeatCheck의 Seatselection는 다름.
// MakeSeat의 버튼을 표시중인데 SeatCheck의 버튼의 배경을 바꿔주고 있음.
//
// * 수정 내용
// MakeSeat에서 설정한 Seatselection을 SeatCheck에서도 사용하게도록 변경