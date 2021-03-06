package src.com.venk.core.game.gobang;
/**
 * 五子棋游戏
 * Gobang
 * 2015-11-19
 * @author Venk
 */
import java.awt.*;
import java.awt.event.*;

public class Gobang extends Frame {

	ChessPad chesspad = new ChessPad();

	Gobang() {
		setVisible(true);
		setLayout(null);
		Label label = new Label("五子棋", Label.CENTER);
		add(label);
		label.setBounds(70, 55, 440, 26);
		label.setBackground(Color.orange);
		add(chesspad);
		chesspad.setBounds(70, 90, 440, 440);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		pack();
		setSize(600, 550);

	}

	public static void main(String args[]) {
		Gobang gobang = new Gobang();

	}
}


class ChessPad extends Panel implements MouseListener, ActionListener {
	int array[][] = new int[19][19];
	Scan scanp = new Scan();
	Scan scanc = new Scan();
	AutoPlay autoPlay = new AutoPlay();
	Evaluate evaluatep = new Evaluate();
	Evaluate evaluatec = new Evaluate();
	Sort sort = new Sort();
	int i = 0;
	int x = -1, y = -1, 棋子颜色 = 1;
	Button button = new Button("重新开局");
	TextField text_1 = new TextField("请黑棋下子"), text_2 = new TextField(),
			text_3 = new TextField();

	ChessPad() {
		setSize(440, 440);
		setLayout(null);
		setBackground(Color.pink);
		addMouseListener(this);
		add(button);
		button.setBounds(10, 5, 60, 26);
		button.addActionListener(this);
		add(text_1);
		text_1.setBounds(90, 5, 90, 24);
		add(text_2);
		text_2.setBounds(290, 5, 90, 24);
		add(text_3);
		text_3.setBounds(200, 5, 80, 24);
		for (int i = 0; i < 19; i++)
			for (int j = 0; j < 19; j++) {
				array[i][j] = 0;
			}
		for (int i = 0; i < 19; i++)
			for (int j = 0; j < 19; j++)
				for (int h = 0; h < 5; h++) {
					scanp.shape[i][j][h] = 0;
					scanc.shape[i][j][h] = 0;
				}
		text_1.setEditable(false);
		text_2.setEditable(false);

	}

	public void paint(Graphics g) {
		for (int i = 40; i <= 400; i = i + 20) {
			g.drawLine(40, i, 400, i);
		}

		for (int j = 40; j <= 400; j = j + 20) {
			g.drawLine(j, 40, j, 400);
		}

		g.fillOval(97, 97, 6, 6);
		g.fillOval(337, 97, 6, 6);
		g.fillOval(97, 337, 6, 6);
		g.fillOval(337, 337, 6, 6);
		g.fillOval(217, 217, 6, 6);
	}

	public void mousePressed(MouseEvent e) {
		int a = 0, b = 0;
		if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
			x = (int) e.getX();
			y = (int) e.getY();
			ChessPoint_black chesspoint_black = new ChessPoint_black(this);
			ChessPoint_white chesspoint_white = new ChessPoint_white(this);
			i++;
			text_3.setText("这是第" + i + "步");

			if ((x + 5) / 20 < 2 || (y + 5) / 20 < 2 || (x - 5) / 20 > 19
					|| (y - 5) / 20 > 19) {
			} else {
				a = (x + 10) / 20;
				b = (y + 10) / 20;
				if (array[b - 2][a - 2] == 0 && 棋子颜色 == 1) {
					this.add(chesspoint_black);
					chesspoint_black.setBounds(a * 20 - 9, b * 20 - 9, 18, 18);
					棋子颜色 = 棋子颜色 * (-1);
					array[b - 2][a - 2] = 1;
					if (Judge.judge(array, 1)) {
						text_1.setText("黑棋赢!");
						棋子颜色 = 2;
						removeMouseListener(this);
					} else {

						text_1.setText("");
					}
				}
			}

			if (i > 2 && 棋子颜色 == -1) {
				scanp.scan(array, 1);
				scanc.scan(array, -1);
				sort.sort(scanp.shape);
				sort.sort(scanc.shape);
				evaluatep.evaluate(scanp.shape);
				evaluatec.evaluate(scanc.shape);

				棋子颜色 = 棋子颜色 * (-1);
				this.add(chesspoint_white);
				if (evaluatep.max > evaluatec.max) {
					text_2.setText(evaluatep.max_x + " " + evaluatep.max_y
							+ " " + evaluatep.max);
					chesspoint_white.setBounds((evaluatep.max_y + 2) * 20 - 9,
							(evaluatep.max_x + 2) * 20 - 9, 18, 18);
					array[evaluatep.max_x][evaluatep.max_y] = -1;
					text_1.setText("请黑棋下子");
					for (int i = 0; i < 19; i++)
						for (int j = 0; j < 19; j++)
							for (int h = 0; h < 5; h++) {
								scanp.shape[i][j][h] = 0;
								scanc.shape[i][j][h] = 0;
							}
				} else

				{
					text_2.setText(evaluatec.max_x + " " + evaluatec.max_y
							+ " " + evaluatec.max);
					chesspoint_white.setBounds((evaluatec.max_y + 2) * 20 - 9,
							(evaluatec.max_x + 2) * 20 - 9, 18, 18);

					array[evaluatec.max_x][evaluatec.max_y] = -1;
					if (Judge.judge(array, -1)) {
						text_2.setText("白棋赢!");
						棋子颜色 = 2;
						removeMouseListener(this);
					} else {

						text_1.setText("请黑棋下子");
						for (int i = 0; i < 19; i++)
							for (int j = 0; j < 19; j++)
								for (int h = 0; h < 5; h++) {
									scanp.shape[i][j][h] = 0;
									scanc.shape[i][j][h] = 0;
								}
					}
				}
			}

			if (i <= 2 && 棋子颜色 == -1) {
				autoPlay.autoPlay(array, b - 2, a - 2);
				this.add(chesspoint_white);
				棋子颜色 = 棋子颜色 * (-1);
				chesspoint_white.setBounds((autoPlay.y + 2) * 20 - 9,
						(autoPlay.x + 2) * 20 - 9, 18, 18);
				array[autoPlay.x][autoPlay.y] = -1;
				if (Judge.judge(array, -1)) {
					text_2.setText("白棋赢!");
					棋子颜色 = 2;
					removeMouseListener(this);
				} else {

					text_1.setText("请黑棋下子");
					text_2.setText(autoPlay.x + " " + autoPlay.y);
				}
			}

		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void actionPerformed(ActionEvent e) {
		this.removeAll();
		棋子颜色 = 1;
		add(button);
		button.setBounds(10, 5, 60, 26);
		add(text_1);
		text_1.setBounds(90, 5, 90, 24);
		text_2.setText("");
		text_1.setText("请黑棋下子");
		add(text_2);
		text_2.setBounds(290, 5, 90, 24);
		add(text_3);
		text_3.setBounds(200, 5, 80, 24);
		i = 0;
		text_3.setText("这是第" + i + "步");
		for (int i = 0; i < 19; i++)
			for (int j = 0; j < 19; j++) {
				array[i][j] = 0;
			}
		for (int i = 0; i < 19; i++)
			for (int j = 0; j < 19; j++)
				for (int h = 0; h < 5; h++) {
					scanp.shape[i][j][h] = 0;
					scanc.shape[i][j][h] = 0;
				}
		addMouseListener(this);
	}
}

class ChessPoint_black extends Canvas implements MouseListener {
	ChessPad chesspad = null;

	ChessPoint_black(ChessPad p) {
		setSize(20, 20);
		addMouseListener(this);
		chesspad = p;
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(0, 0, 18, 18);
	}

	public void mousePressed(MouseEvent e) {
		/*
		 * if(e.getModifiers()==InputEvent.BUTTON3_MASK){ chesspad.remove(this);
		 * chesspad.棋子颜色=1; chesspad.text_2.setText("");
		 * chesspad.text_1.setText("请黑棋下子"); }
		 */
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {

	}
}

class ChessPoint_white extends Canvas implements MouseListener {
	ChessPad chesspad = null;

	ChessPoint_white(ChessPad p) {
		setSize(20, 20);
		addMouseListener(this);
		chesspad = p;
	}

	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(0, 0, 18, 18);
	}

	public void mousePressed(MouseEvent e) {
		/*
		 * if(e.getModifiers()==InputEvent.BUTTON3_MASK){ chesspad.remove(this);
		 * chesspad.棋子颜色=-1; chesspad.text_2.setText("请白棋下子");
		 * chesspad.text_1.setText(""); }
		 */
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {

	}
}


class AutoPlay {
	int x, y;

	void autoPlay(int chesspad[][], int a, int b) {
		int randomNumber = (int) (Math.random() * 8) + 1;
		switch (randomNumber) {
		case (1):
			if (chesspad[a - 1][b - 1] == 0) {
				x = a - 1;
				y = b - 1;
			} else if (chesspad[a - 2][b - 2] == 0) {
				x = a - 2;
				y = b - 2;
			} else {
				x = a - 3;
				y = b - 3;
			}
			break;

		case (2):
			if (chesspad[a - 1][b] == 0) {
				x = a - 1;
				y = b;
			} else if (chesspad[a - 2][b] == 0) {
				x = a - 2;
				y = b;
			} else {
				x = a - 3;
				y = b;
			}
			break;
		case (3):
			if (chesspad[a - 1][b + 1] == 0) {
				x = a - 1;
				y = b + 1;
			} else if (chesspad[a - 2][b + 2] == 0) {
				x = a - 2;
				y = b + 2;
			} else {
				x = a - 3;
				y = b + 3;
			}
			break;
		case (4):
			if (chesspad[a][b + 1] == 0) {
				x = a;
				y = b + 1;
			} else if (chesspad[a][b + 2] == 0) {
				x = a;
				y = b + 2;
			} else {
				x = a;
				y = b + 3;
			}
			break;
		case (5):
			if (chesspad[a + 1][b + 1] == 0) {
				x = a + 1;
				y = b + 1;
			} else if (chesspad[a + 2][b + 2] == 0) {
				x = a + 2;
				y = b + 2;
			} else {
				x = a + 3;
				y = b + 3;
			}
			break;
		case (6):
			if (chesspad[a + 1][b] == 0) {
				x = a + 1;
				y = b;
			} else if (chesspad[a + 2][b] == 0) {
				x = a + 2;
				y = b;
			} else {
				x = a + 3;
				y = b;
			}
			break;
		case (7):
			if (chesspad[a + 1][b - 1] == 0) {
				x = a + 1;
				y = b - 1;
			} else if (chesspad[a + 2][b - 2] == 0) {
				x = a + 2;
				y = b - 2;
			} else {
				x = a + 3;
				y = b - 3;
			}
			break;
		case (8):
			if (chesspad[a][b - 1] == 0) {
				x = a;
				y = b - 1;
			} else if (chesspad[a][b - 2] == 0) {
				x = a;
				y = b - 2;
			} else {
				x = a;
				y = b + 3;
			}
			break;
		}
	}
}

class Evaluate {
	int max_x, max_y, max;

	public void evaluate(int shape[][][]) {
		int i = 0, j = 0;

		for (i = 0; i < 19; i++)
			for (j = 0; j < 19; j++) {

				switch (shape[i][j][0]) {
				case 5:
					shape[i][j][4] = 200;
					break;
				case 4:
					switch (shape[i][j][1]) {
					case 4:
						shape[i][j][4] = 150 + shape[i][j][2] + shape[i][j][3];

						break;
					case 3:
						shape[i][j][4] = 100 + shape[i][j][2] + shape[i][j][3];
						break;
					default:
						shape[i][j][4] = 50 + shape[i][j][2] + shape[i][j][3];

					}
					break;
				case 3:
					switch (shape[i][j][1]) {
					case 3:
						shape[i][j][4] = 75 + shape[i][j][2] + shape[i][j][3];
						break;
					default:
						shape[i][j][4] = 20 + shape[i][j][2] + shape[i][j][3];
					}
					break;
				case 2:
					shape[i][j][4] = 10 + shape[i][j][1] + shape[i][j][2]
							+ shape[i][j][3];
					break;
				case 1:
					shape[i][j][4] = shape[i][j][0] + shape[i][j][1]
							+ shape[i][j][2] + shape[i][j][3];
				default:
					shape[i][j][4] = 0;
				}
			}

		int x = 0, y = 0;
		max = 0;
		for (x = 0; x < 19; x++)
			for (y = 0; y < 19; y++)
				if (max < shape[x][y][4]) {
					max = shape[x][y][4];
					max_x = x;
					max_y = y;
				}
	}
}

class Judge {

	static boolean judge(int a[][], int color) {

		int i, j, flag;
		for (i = 0; i < 19; i++) {
			flag = 0;
			for (j = 0; j < 19; j++)
				if (a[i][j] == color) {
					flag++;
					if (flag == 5)
						return true;
				} else
					flag = 0;

		}
		for (j = 0; j < 19; j++) {
			flag = 0;
			for (i = 0; i < 19; i++)
				if (a[i][j] == color) {
					flag++;
					if (flag == 5)
						return true;
				} else
					flag = 0;
		}
		for (j = 4; j < 19; j++) {
			flag = 0;
			int m = j;
			for (i = 0; i <= j; i++) {

				if (a[i][m--] == color) {
					flag++;
					if (flag == 5)
						return true;
				} else
					flag = 0;
			}
		}
		for (j = 14; j >= 0; j--) {
			flag = 0;
			int m = j;
			for (i = 0; i <= 18 - j; i++) {

				if (a[i][m++] == color) {
					flag++;
					if (flag == 5)
						return true;
				} else
					flag = 0;
			}
		}
		for (i = 14; i >= 0; i--) {
			flag = 0;
			int n = i;
			for (j = 0; j < 19 - i; j++) {

				if (a[n++][j] == color) {
					flag++;
					if (flag == 5)
						return true;
				} else
					flag = 0;
			}
		}
		for (j = 14; j >= 0; j--) {
			flag = 0;
			int m = j;
			for (i = 18; i >= j; i--) {

				if (a[i][m++] == color) {
					flag++;
					if (flag == 5)
						return true;
				} else
					flag = 0;
			}
		}

		return false;
	}
}

class Scan {

	int shape[][][] = new int[19][19][5];

	void scan(int chesspad[][], int colour) {
		int i, j;

		for (i = 0; i <= 18; i++)
			for (j = 0; j <= 18; j++)
				if (chesspad[i][j] == 0) {
					int m = i, n = j;

					while (n - 1 >= 0 && chesspad[m][--n] == colour) {
						shape[i][j][0]++;
					}
					n = j;
					while (n + 1 <= 18 && chesspad[m][++n] == colour) {
						shape[i][j][0]++;
					}

					n = j;
					while (m - 1 >= 0 && chesspad[--m][n] == colour) {
						shape[i][j][1]++;
					}
					m = i;
					while (m + 1 <= 18 && chesspad[++m][n] == colour) {
						shape[i][j][1]++;
					}

					m = i;
					while (m - 1 >= 0 && n + 1 <= 18
							&& chesspad[--m][++n] == colour) {
						shape[i][j][2]++;
					}
					m = i;
					n = j;
					while (m + 1 <= 18 && n - 1 >= 0
							&& chesspad[++m][--n] == colour) {
						shape[i][j][2]++;
					}

					m = i;
					n = j;

					while (m - 1 >= 0 && n - 1 >= 0
							&& chesspad[--m][--n] == colour) {
						shape[i][j][3]++;
					}
					m = i;
					n = j;
					while (m + 1 <= 18 && n + 1 <= 18
							&& chesspad[++m][++n] == colour) {
						shape[i][j][3]++;
					}

				}

	}

}

class Sort {

	public void sort(int shape[][][]) {

		int temp;
		for (int i = 0; i < 19; i++)
			for (int j = 0; j < 19; j++) {

				for (int h = 1; h <= 4; h++) {
					for (int w = 3; w >= h; w--) {
						if (shape[i][j][w - 1] < shape[i][j][w]) {
							temp = shape[i][j][w - 1];
							shape[i][j][w - 1] = shape[i][j][w];
							shape[i][j][w] = temp;
						}
					}
				}
			}
	}
}
