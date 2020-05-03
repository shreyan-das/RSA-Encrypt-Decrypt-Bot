import java.util.*;

public class Rsa {
	static int u = 0;
	static int v = 0;
	static ArrayList<Integer> enli = new ArrayList<Integer>();

	public static boolean primality(int num) {
		if (num < 1) {
			return false;
		}
		for (int i = 2; i < num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static int binToDec(int bin) {
		String binString = Integer.toString(bin);
		return Integer.parseInt(binString, 2);
	}

	public static int gcd(int x, int y) {
		int xVar = x;
		int yVar = y;
		int mod;
		int uP = 1;
		int uC = 0;
		int vP = 0;
		int vC = 1;
		int uCC;
		int vCC;
		int q = 0;
		while (yVar != 0) {
			q = xVar / yVar;
			mod = xVar % yVar;
			xVar = yVar;
			yVar = mod;
			uCC = uP - q * uC;
			vCC = vP - q * vC;
			uP = uC;
			uC = uCC;
			vP = vC;
			vC = vCC;

		}
		u = uP;
		v = vP;
		return x;
	}

	public static void yList(int z, int m, int n) {
		enli.add(z % m);

		for (int i = 0; i < n; i++) {
			enli.add((enli.get(i) * 2) % m);
		}
	}

	public static int solution(int z, int a, int m) {
		int b = Integer.parseInt(Integer.toBinaryString(a));
		yList(z, m, Integer.toString(b).length() - 1);
		ArrayList<Integer> list = (ArrayList<Integer>)enli.clone();
		int mult = 1;
		for (int i = 0; i < Integer.toString(b).length(); i++) {
			if (Integer.toString(b).charAt(i) == '1') {
				mult = mult * list.get(i);
				mult %= m;
			}
		}
		return mult;
	}

	public static int function(int k, int b, int p, int q) {
		int thrown = gcd(k, (p - 1) * (q - 1));
		int power = u;
		return solution(b, power, (p * q));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(
				"Welcome to the RSA Encryption/Decryption Bot. \n \n Would you like to encrypt or decrypt a message? (E/D)");
		Scanner in = new Scanner(System.in);
		String cryptChoice = in.next();
		in.nextLine();
		if (cryptChoice.toLowerCase().equals("e")) {
			int p = 0;
			int q = 0;
			while (p == q || primality(p) == false || primality(q) == false) {
				System.out.println("Choose your p value:");
				p = in.nextInt();
				in.nextLine();
				System.out.println("Choose your q value:");
				q = in.nextInt();
				in.nextLine();
				
				if(p == q || primality(p) == false || primality(q) == false) {
					System.out.println("Invalid number combination. Use numbers that are unequal primes and coprime.");
				}
			}
			System.out.println("Your p value " + p + " and your q value " + q + " are your private keys. Only you and the receive should know these.");
			System.out.println("Now choose a k value: (No restrictions) This is one of your public keys, along with your p*q, which is " + p*q + ".");
			int k = in.nextInt();
			in.nextLine();
			System.out.println("Enter s, a number of which you are try to send securely to the receiver. This number should be less than your value p*q.");
			int s = in.nextInt();
			in.nextLine();
			System.out.println("Your e value is " + solution(s,k,p*q) + ", which is what you will also send publicly to the sender.");
			System.out.println("Your encryption is complete! Make sure to send the receiver your e, k, and m(which is your p*q) values, without worry of encryption. \nP and q should be kept private between you and the sender.");
		}else if(cryptChoice.toLowerCase().equals("d")) {
			System.out.println("Enter the keys that you received from the sender");
			System.out.println("Enter your e value:");
			int e = in.nextInt();
			in.nextLine();
			System.out.println("Enter your k value:");
			int k = in.nextInt();
			in.nextLine();
			System.out.println("Now, use your private p and q values.");
			int p = 0;
			int q = 0;
			while (p == q || primality(p) == false || primality(q) == false) {
				System.out.println("Enter your p value:");
				p = in.nextInt();
				in.nextLine();
				System.out.println("Enter your q value:");
				q = in.nextInt();
				in.nextLine();
				if(p == q || primality(p) == false || primality(q) == false) {
					System.out.println("Invalid number combination. Use numbers that are unequal primes and coprime.");
				}
			}
			System.out.println("Your decoded message, s, is " + 2*function(k,e,p,q) + ". Do what you would like with that information!");
			
		}else {
			System.out.println("Invalid input. Rerun and try again.");
		}
		
	}

}
