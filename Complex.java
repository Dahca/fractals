/**
 * Class that represents a complex number, with associated methods.
 */

public class Complex implements Comparable<Complex> {

  public static final Complex ZERO = new Complex();
  public static final Complex ONE = new Complex(1, 0);
  public static final Complex I = new Complex(0, 1);

  private double re;
  private double im;

  public Complex() {
    this.re = 0;
    this.im = 0;
  }

  public Complex(double re, double im) {
    this.re = re;
    this.im = im;
  }

  public Complex(Complex input) {
    this.re = input.getRe();
    this.im = input.getIm();
  }

  public double getRe() {
    return this.re;
  }

  public double getIm() {
    return this.im;
  }

  public void setRe(double re) {
    this.re = re;
  }

  public void setIm(double im) {
    this.im = im;
  }

  public Complex add(Complex op) {
    return new Complex(this.re + op.re, this.im + op.im);
  }

  public Complex sub(Complex op) {
    return new Complex(this.re - op.re, this.im - op.im);
  }

  public Complex mul(Complex op) {
    double re = this.re * op.getRe() - this.im * op.getIm();
    double im = this.re * op.getIm() + this.im * op.getRe();
    return new Complex(re, im);
  }

  public Complex pow(int p) {
    if (p == 0) return new Complex(1, 0);
    if (p == 1) return this;
    return this.mul(pow(p-1));
  }

  public double dist(Complex op) {
    return Math.sqrt( Math.pow(this.re - op.getRe(), 2)
                    + Math.pow(this.im - op.getIm(), 2) );
  }

  public Complex div(Complex op) {
    Complex result = new Complex(this);
    result = result.mul(op.getConjugate());
    double opNormSq = (op.getRe()*op.getRe()) + (op.getIm()*op.getIm());
    result.setRe(result.getRe() / opNormSq);
    result.setIm(result.getIm() / opNormSq);
    return result;
  }
  public Complex fromPolar(double magnitude, double angle) {
    double re = magnitude * Math.cos(angle);
    double im = magnitude * Math.sin(angle);
    return new Complex(re, im);
  }

  public double getNorm() {
    return Math.sqrt(this.re * this.re + this.im * this.im);
  }

  public double getAngle() {
    return Math.atan2(this.im, this.re);
  }

  public Complex getConjugate() {
    return new Complex(this.re, this.im * (-1.0));
  }

  public String toString() {
    if (this.re == 0) {
      if (this.im == 0) {
        return "0";
      } else {
        return (this.im + "i");
      }
    } else {
      if (this.im == 0) {
        return String.valueOf(this.re);
      } else if (this.im < 0) {
        return(this.re + " " + this.im + "i");
      } else {
        return(this.re + " +" + this.im + "i");
      }
    }
  }

  @Override
  public int compareTo(Complex c) {
    if (this.re == c.re && this.im == c.im) return 0;

    double res = this.dist(ZERO) - c.dist(ZERO);
    if (res <= 0.0) return -1;
    return 1;
  }

  public boolean equals(Complex c) {
    if( c.re == this.re && c.im == this.im )
      return true;
    return false;
  }

  public static void main(String argv[]) {
    Complex a = new Complex(3, 4);
    Complex b = new Complex(1, -100);
    System.out.println(a.getNorm());
    System.out.println(b.getAngle());
    System.out.println(a.mul(b));
    System.out.println(a.div(b));
    System.out.println(a.div(b).mul(b));
  }
}
