package Sokoban;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Soko extends JFrame implements KeyListener{
	
	public static final int SIZE_LINE_THICK=3;
	public static final int SIZE_TITLE_THICK=28;
	
	public static final int SIZE_TILE_X= 20; //���� �ȼ� ��
	public static final int SIZE_TILE_Y= 15; //���� �ȼ� ��
	
	public static final int SIZE_IMAGE_X= 40; //�̹��� ������
	public static final int SIZE_IMAGE_Y= 40; //�̹��� ������
	
	public static final int MV_OFFSET= SIZE_IMAGE_X;
	public static final String Title = "Sokoban";
	
	Image IBox = Toolkit.getDefaultToolkit().getImage("Box.png");	
	Image IWall = Toolkit.getDefaultToolkit().getImage("Wall.png");	
	Image IWall2 = Toolkit.getDefaultToolkit().getImage("Wall2.png");	
	Image IRoad = Toolkit.getDefaultToolkit().getImage("Road.png");	
	Image IDot = Toolkit.getDefaultToolkit().getImage("Dot.png");	
	
	Image IRoadG = Toolkit.getDefaultToolkit().getImage("RoadG.png");	
	Image IRoadY = Toolkit.getDefaultToolkit().getImage("RoadY.png");	
	Image IRoadR = Toolkit.getDefaultToolkit().getImage("RoadR.png");
	Image IFire = Toolkit.getDefaultToolkit().getImage("fire.png");
	
	Image IPersonFront = Toolkit.getDefaultToolkit().getImage("PersonF.png");
	Image IPersonBack = Toolkit.getDefaultToolkit().getImage("PersonB.png");
	Image IPersonLEFT = Toolkit.getDefaultToolkit().getImage("PersonL.png");
	Image IPersonRIGHT = Toolkit.getDefaultToolkit().getImage("PersonR.png");
	Image IPersonFire = Toolkit.getDefaultToolkit().getImage("PersonFire.png");
	Image IBoxFire = Toolkit.getDefaultToolkit().getImage("FireBox.png");
	//�̹����� ������ ����
	
	Image IPerson = IPersonFront; 
	
	int IKeyCount =0; //������ ��
	int IPersonX =0; //��ġ
	int IPersonY =0; //��ġ
	int IStage =0; //��������
	int IPersonOldX=0; //������ġ
	int IPersonOldY=0; //������ġ
	char[][] Map = new char[SIZE_TILE_X][SIZE_TILE_Y];
	
	String[][] Stage ={{ "####################", // 1����
						 "#@ R     ##     R  #",
						 "#   B   Y #        #",
						 "# G####   #Y     . #",
						 "#  Y###  #####  #  #",
						 "#    ##      #  #  #",
						 "#         ##    #  #",
						 "#####    #   B #####",
						 "#    ##   #        #",
						 "# B       #  Y     #",
						 "#   ##    ###### R #",
						 "# F ##   G     #   #",
						 "#   ##      .      #",
						 "# R ## . Y       G #",
						 "####################"
						},
					   { "####################", // 2����
						 "#@G            #####",
					     "#    B    R        #",
					     "############       #",
						 "#        F     Y   #",
						 "#  B             RR#",
						 "#     ##############",
						 "# G  R             #",
						 "#                  #",
						 "############    R  #",
						 "# B                #",
						 "#                 Y#",
						 "#      #############",
						 "# Y      G      ...#",
						 "####################"
							},
						{"####################", //3����
						 "#@    G  #   G     #",
						 "#    B      Y      #",
						 "################   #",
						 "#    #########     #",
						 "#  F  ######    B  #",
						 "# B     ###        #",
						 "#     G  B         #",
						 "#    ##########G   #",
						 "#G      .#.       ##",
						 "#   R   ###  R    ##",
						 "#     #######      #",
						 "#.  ############ R.#",
						 "####################",
						 "####################"
						},
						{"####################", //4����
						 "#@     ####S   .####",
						 "# GR   ####   G  ###",
						 "#  B ######        #",
						 "#   ######### G    #",
						 "#   #########   F  #",
						 "#    #######    ####",
						 "###    B       #####",
						 "###   R      Y     #",
						 "# G   ###  ##B     #",
						 "#      ##..##     ##",
						 "#    R  ####    ####",
						 "#####   Y       ####",
						 "######      G  #####",
						 "####################"
						},
						{"####################", //5����
						 "#@       #         #",
						 "#   R########## R  #",
						 "#  Y $.    Y       #",
						 "#    $.        R   #",
						 "#    #########    G#",
						 "#F    B       $    #",
						 "# R   B   G   $    #",
						 "#            F$    #",
						 "#  Y ######### Y R #",
						 "#  G     Y         #",
						 "####     G    R ####",
						 "####################",
						 "####################",
						 "####################"
								},
						{"####################", //6����
						 "#@   Y ##    F     #",
						 "#    ##     #   B  #",
						 "# #Y       R  ###  #",
						 "#     ###          #",
						 "###    Y    #### G #",
						 "#    ###   R     ###",
						 "#  G        # #   ##",
						 "##### B           Y#",
						 "#     ##    R  #####",
						 "#F #      ### d#  ##",
						 "# ##    Y  G     Y #",
						 "#     #   ### #    #",
						 "#.. R      G       #",
						 "####################"
									},
						{"####################", //7����
						 "#@ ##       ##  F  #",
						 "#..##  B R  ##     #",
						 "#  ##  ##   ##Y    #",
						 "#  ##Y ##   ##  #  #",
						 "#R ##  ##G  ##  #  #",
						 "#  ##  ##   ##B # R#",
						 "#G ##  ##R  ##  #  #",
						 "#  ##R ##   ##  #  #",
						 "#  ##  ## Y ##  #  #",
						 "#  ##  ##   ##  # R#",
						 "#  ##G ##   ##  #  #",
						 "#      ##R   Y  #  #",
						 "#   R  ##       #  #",
						 "####################"
										},
						{"####################", //8����
						 "####################",
						 "##.        R     .##",
						 "##   Y  #    #    ##",
						 "###    Y   G     ###",
						 "####  # #   $   ####",
						 "#####          #####",
						 "######## @  ########",
						 "########    ########",
						 "#####          #####",
						 "####   Y   R    ####",
						 "###  B    #   B  ###",
						 "##     # F        ##",
						 "####################",
						 "####################"
											},
						{"####################", //9����
						 "## @  ##############",
						 "### B  #############",
						 "####    ############",
						 "#####    ###########",
						 "###### Y  ##########",
						 "#######        #####",
						 "####     # G     ###",
						 "#    G   #  ###  ###",
						 "#  ###   ##   G  Y #",
						 "#             ######",
						 "#    ######       ##",
						 "## #    ###      .##",
						 "#####  Y     F   ###",
						 "####################"
												},
						{"####################", //10����
						 "#@  # #    .   $   #",
						 "# B   B #    R    ##",
						 "# #  R   #  F   #  #",
						 "#   # #    #    #  #",
						 "#G .     #    . #  #",
						 "# #   $ R BG  #    #",
						 "#.   #    #      # #",
						 "#  #  #  #   #   B #",
						 "#   G   Y   .      #",
						 "# #   #   #  $  #  #",
						 "#.  Y G   #      Y #",
						 "##    #  B    B #  #",
						 "#  F   #     #   F##",
						 "####################"
																			
						},
											
						};

	
	void LoadMap() {
		//map[0] = stage[0][0]
		//map[1] = stage[0][1]
		for(int i = 0; i < SIZE_TILE_Y ; ++i) {
			Map[i]= Stage[IStage][i].toCharArray();		
			IKeyCount =0;
		}	
	
	}

	public Soko(){
		LoadMap();	//map �迭 �ε�
		Container containerObj = this.getContentPane();
		containerObj.setLayout(null);
		containerObj.addKeyListener(this);
		containerObj.setFocusable(true);
		containerObj.requestFocus();
		
		this.setSize(SIZE_TILE_X*SIZE_IMAGE_X+SIZE_LINE_THICK*2,
				     SIZE_TILE_Y*SIZE_IMAGE_Y+SIZE_LINE_THICK*2+SIZE_TITLE_THICK);
		this.setResizable(false);
		this.setVisible(true);		
		this.setLocationRelativeTo(null);
		this.setTitle(Title+ " [Stage: "+ (IStage+1) +"]");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
	@Override
	public void paint(Graphics g) {
		Image iTemp = IPerson;	

		while(true) {
			boolean isEndGame = true;
			
			for(int i = 0; i < SIZE_TILE_Y ; ++i) {
			      for(int j = 0; j < SIZE_TILE_X; ++j) {
			    	  
			    	System.out.print(Map[i][j]);		
			    	
			    	  switch(Map[i][j]){
				    	  case '#': 
				    		  	iTemp =IWall;
				    	  		break;
				    	  case '@':
				    		  	iTemp = IPerson;	
				    	  		IPersonX = j; //1
				    	  		IPersonY = i; //2, �Ʒ�Ű�� ������ 3�̵ȴ�
				    	  		
				    	  		break;
				    	  case ' ': 
				    		  	iTemp =IRoad;
				    	  		break;
				    	  		
				    	  case 'B': 
				    		  if('.' != Stage[IStage][i].charAt(j)) {
				    			  isEndGame = false;  		  
					    	  }
				    		  	iTemp =IBox;
				    	  		break;			    	  		
				    	  case '.': 
				    		  	iTemp =IDot;
				    	  		break;
				    	  case 'G':
				    		  	iTemp =IRoadG;
				    	  		break;
				    	  case 'Y': 
				    		  	iTemp =IRoadY;
				    	  		break;
				    	  case 'R':  
				    		  	iTemp =IRoadR;	  
				    	  		break;
				    	  case '$':  
				    		  	iTemp =IWall2;	  
				    	  		break;

				    	  case 'F':  
				    		  	iTemp =IFire;	  
				    	  		break;
				    	  		
				    	  case 'Q':  
				    		  	iTemp =IBoxFire;	  
				    	  		break;
				    	  
				    	  case 'P':
				    		  	iTemp = IPersonFire;
				    		  	break;
				    	  
				    	  default:
				    		  continue;
			    	  }   
			    	  
			    	  g.drawImage(iTemp, //iTemp�� �׸���
			    			  
		    				  SIZE_LINE_THICK                   +j*SIZE_IMAGE_X, 
		    				  SIZE_LINE_THICK +SIZE_TITLE_THICK +i*SIZE_IMAGE_Y, 
		    				  SIZE_IMAGE_X, 
		    				  SIZE_IMAGE_Y, 
		    				  
		    				  this);		    	  	    	
			      }
			      System.out.println();
	
			      
			}	
			
			if(isEndGame == true) {
				++IStage;
				System.out.println(Stage.length);
				
				if(Stage.length == IStage) {
					JOptionPane.showConfirmDialog(null, "ALL CLEAR");
					System.exit(0);
					
				}
				
				JOptionPane.showConfirmDialog(null, "CLEAR, ���������� �̵��մϴ�");
				LoadMap();
				this.setTitle(Title+ " [Stage: "+ (IStage+1) +"]");
				continue;
			}
			break;
		}
	}

	void movePerson() {
		
		//������ġ�� ' '�ΰ��
		if(' ' == Map[IPersonY][IPersonX]) {
			
			//�ҿ� �ִ� �����϶�
			if('P' == Map[IPersonOldY][IPersonOldX]) {
				JOptionPane.showConfirmDialog(null, "�� �߰ſ�! �ҿ� ��ҳ�����!");
				LoadMap();
				this.setTitle(Title+ " [Stage: "+ (IStage+1) +"]" ); 
				repaint();
				return;
			}
		
		}
		
		//������ġ�� '#' �� ���
		if('#' == Map[IPersonY][IPersonX]) {
			//�ҿ� �ִ� �����϶�
			if('P' == Map[IPersonOldY][IPersonOldX]) {
				JOptionPane.showConfirmDialog(null, "�� �߰ſ�! �ҿ� ��ҳ�����!");
				LoadMap();
				this.setTitle(Title+ " [Stage: "+ (IStage+1) +"]" ); 
				repaint();
				return;
			}
			//if���� �ƴϸ� �ƹ���ȭ ����
			return;
		}
		
		//������ġ�� '$' �� ���
		if('$' == Map[IPersonY][IPersonX]) {
			//�ҿ� �ִ� �����϶�
			if('P' == Map[IPersonOldY][IPersonOldX]) {
				JOptionPane.showConfirmDialog(null, "�� �߰ſ�! �ҿ� ��ҳ�����!");
				LoadMap();
				this.setTitle(Title+ " [Stage: "+ (IStage+1) +"]" ); 
				repaint();
				return;
			}
			//if���� �ƴϸ� �ƹ���ȭ ����
			return;
		}
		
		//�ҿ� ����� ��
		if('F' ==  Map[IPersonY][IPersonX]){
			//�ҿ� ������ ���� ��ġ�� ���ڸ� P�� �ٲ�
			Map[IPersonY][IPersonX] = 'P';
			
			//oldŸ�� ó��
			if('.' == Stage[IStage][IPersonOldY].charAt(IPersonOldX)){    
			      	Map[IPersonOldY][IPersonOldX] = '.';
					
			    }else if('Y' == Stage[IStage][IPersonOldY].charAt(IPersonOldX)){
			    	Map[IPersonOldY][IPersonOldX] = 'Y';
			   
			    }else if('R' == Stage[IStage][IPersonOldY].charAt(IPersonOldX)){
				    Map[IPersonOldY][IPersonOldX] = 'R';
					
				}else if('G' == Stage[IStage][IPersonOldY].charAt(IPersonOldX)){
				      Map[IPersonOldY][IPersonOldX] = 'G';
					 
				}else {
			    	Map[IPersonOldY][IPersonOldX] = ' ';  
			    }					
			return;
		}
		
		
		if('B' == Map[IPersonY][IPersonX]) {
			
			//�ҿ� �ε�����
		     if('F' == Map[2*IPersonY - IPersonOldY][2*IPersonX - IPersonOldX]) {
	
				Map[2*IPersonY - IPersonOldY][2*IPersonX - IPersonOldX] = 'Q'; 
				Map[IPersonY][IPersonX] = '@';
				++IKeyCount;
				
				if(IKeyCount == 1000) {
					JOptionPane.showConfirmDialog(null, "�ʹ� ���� ������ ���Ƴ�����...");
					LoadMap();
					this.setTitle(Title+ " [Stage: "+ (IStage+1) +"]" ); 
					repaint();
					return;
				}
				
				//oldŸ�� ó��
				if('.' == Stage[IStage][IPersonOldY].charAt(IPersonOldX)){    
				      	Map[IPersonOldY][IPersonOldX] = '.';
						
				    }else if('Y' == Stage[IStage][IPersonOldY].charAt(IPersonOldX)){
				    	Map[IPersonOldY][IPersonOldX] = 'Y';
				   
				    }else if('R' == Stage[IStage][IPersonOldY].charAt(IPersonOldX)){
					    Map[IPersonOldY][IPersonOldX] = 'R';
						
					}else if('G' == Stage[IStage][IPersonOldY].charAt(IPersonOldX)){
					      Map[IPersonOldY][IPersonOldX] = 'G';
						 
					}else {
				    	Map[IPersonOldY][IPersonOldX] = ' ';  
				    }			
				
				this.setTitle(Title+ " [Stage: "+ (IStage+1) +"]" + "[Move: " + IKeyCount+ "]"); 
				
				return;
		    	 
			  }
			
			//�ڽ��� ����, �ڽ��� �ڽ��� �ε�����
			if('B' == Map[2*IPersonY - IPersonOldY][2*IPersonX - IPersonOldX]) {
		        return;
		     }
			
		     if('#' == Map[2*IPersonY - IPersonOldY][2*IPersonX - IPersonOldX]) {
		        return;
		     }
		     
		     
		     //��ó��
		     if('P' == Map[IPersonOldY][IPersonOldX]) {
					JOptionPane.showConfirmDialog(null, "�� �߰ſ�! �ҿ� ��ҳ�����!");
					LoadMap();
					this.setTitle(Title+ " [Stage: "+ (IStage+1) +"]" ); 
					repaint();
					return;
				}
		     
		     Map[2*IPersonY - IPersonOldY][2*IPersonX - IPersonOldX] = 'B';
		     		    
		 
		}		
		if('Q' == Map[IPersonY][IPersonX]) {
			JOptionPane.showConfirmDialog(null, "�ڽ��� �ҿ� Ÿ�� ������ ���� �����...");
			LoadMap();
			this.setTitle(Title+ " [Stage: "+ (IStage+1) +"]" ); 
			repaint();
			return;
		}
		
		
		//��� �������°� ó��
		if('.' == Stage[IStage][IPersonOldY].charAt(IPersonOldX)){    
		      Map[IPersonOldY][IPersonOldX] = '.';
		    } else if('Y' == Stage[IStage][IPersonOldY].charAt(IPersonOldX)){
		      Map[IPersonOldY][IPersonOldX] = 'Y';
		    }else if('R' == Stage[IStage][IPersonOldY].charAt(IPersonOldX)){
			      Map[IPersonOldY][IPersonOldX] = 'R';
			}else if('G' == Stage[IStage][IPersonOldY].charAt(IPersonOldX)){
			      Map[IPersonOldY][IPersonOldX] = 'G';		      
			}else {
		    	Map[IPersonOldY][IPersonOldX] = ' ';   	
		    }
		
	
		Map[IPersonY][IPersonX] = '@';
		++IKeyCount;
		
		if(IKeyCount == 1000) {
			JOptionPane.showConfirmDialog(null, "�ʹ� ���� ������ ���Ƴ�����...");
			LoadMap();
			this.setTitle(Title+ " [Stage: "+ (IStage+1) +"]" ); 
			repaint();
			return;
		}
		
		this.setTitle(Title+ " [Stage: "+ (IStage+1) +"]" + "[Move: " + IKeyCount+ "]"); 
		
		System.out.println("IPersonX:" + IPersonX);
		System.out.println("IPersonY:" + IPersonY);
		System.out.println("IPersonOldX:" + IPersonOldX);
		System.out.println("IPersonOldY:" + IPersonOldY);
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		IPersonOldX=IPersonX;
		IPersonOldY=IPersonY;
		
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:	
				--IPersonY;
				IPerson = IPersonBack;
				break;
			case KeyEvent.VK_DOWN:
				++IPersonY;
				IPerson = IPersonFront;
				break;
			case KeyEvent.VK_LEFT:
				--IPersonX;
				IPerson = IPersonLEFT;
				break;
			case KeyEvent.VK_RIGHT:
				++IPersonX;
				IPerson = IPersonRIGHT;
				break;
			case KeyEvent.VK_PAGE_DOWN:
				JOptionPane.showConfirmDialog(null, "���������� �̵��մϴ�.");
				--IStage;
				if(0 > IStage) {
					IStage = 0;
				}
				LoadMap();
				this.setTitle(Title+" [Stage:"+ (IStage+1) +"]");
				repaint();
				return;
			case KeyEvent.VK_PAGE_UP:
				JOptionPane.showConfirmDialog(null, "���������� �̵��մϴ�.");
				++IStage;
				if(Stage.length <= IStage) {
					IStage = Stage.length - 1;
				}
			case KeyEvent.VK_HOME:
				LoadMap();
				this.setTitle(Title+" [Stage:"+ (IStage+1) +"]");
				repaint();
				return;
			default:
				return;
		}	
		
		movePerson();	
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

}