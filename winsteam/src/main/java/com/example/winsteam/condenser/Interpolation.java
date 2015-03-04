package com.example.winsteam.condenser;

public class Interpolation {
	/**
	 * 一元全区间不等距插值 8节点
	 * 用拉格朗日插值公式计算指定点处的函数近似值
	 * @param x	 记录n个节点
	 * @param y	记录已知n个节点的函数值 
	 * @param t	待求插值点
	 * @return	t处的函数近似值
	 */
	public static double en_lagrange(double[] x, double[] y, double t) {
		int i, j, k, l, n = x.length;
		double s, f;
		for (i = 0; i < n; i++) {
			if (t == x[i])
				return y[i];
			if (t < x[i])
				break;
		}
		if (i < 4) {
			k = 0;
			l = i + 4;
		} else if (i >= n - 4) {
			k = i - 4;
			l = n;
		} else {
			k = i - 4;
			l = i + 4;
		}
		f = 0;
		for (i = k; i < l; i++) {
			s = 1;
			for (j = k; j < i; j++)
				s *= (t - x[j]) / (x[i] - x[j]);
			for (j = i + 1; j < l; j++)
				s *= (t - x[j]) / (x[i] - x[j]);
			f += s * y[i];
		}
		return f;
	}

	/**
	 * 一元全区间不等距插值公式 全部节点
	 * 用拉格朗日插值公式计算指定点处函数近似值
	 * @param x	 记录n个节点
	 * @param y	记录已知n个节点的函数值 
	 * @param t	待求插值点
	 * @return	t处的函数近似值
	 */
	public static double en_lagrange2(double[] x, double[] y, double t) {
		int i, j, n = x.length;
		double s, g = 0;
		for (i = 0; i < n; i++) {
			s = 1;
			for (j = 0; j < i; j++)
				s *= (t - x[j]) / (x[i] - x[j]);
			for (j = i + 1; j < n; j++)
				s *= (t - x[j]) / (x[i] - x[j]);
			g += s * y[i];
		}
		return g;
	}

	/**
	 * 一元全区间等距插值 8节点
	 * 用拉格朗日插值公式计算指定点处函数近似值
	 * @param x	记录n个节点最左边节点x0=a的数值
	 * @param h	记录节点间距(b-a)/n
	 * @param n	全部节点的个数为n+1
	 * @param y	记录已知n+1个节点的函数值
	 * @param t	待求插值点
	 * @return	t处的函数近似值
	 */
	public static double ee_lagrange(double x, double h, int n, double[] y, double t) {
		int i, j, k, l;
		double s, f;
		for (i = 0; i < n; i++) {
			if (t == x + i * h)
				return y[i];
			if (t < x + i * h)
				break;
		}
		if (i < 4) {
			k = 0;
			l = i + 4;
		} else if (i >= n - 4) {
			k = i - 4;
			l = n;
		} else {
			k = i - 4;
			l = i + 4;
		}
		f = 0;
		for (i = k; i < l; i++) {
			s = 1;
			for (j = k; j < i; j++)
				s *= (t - x - j * h) / (i - j) / h;
			for (j = i + 1; j < l; j++)
				s *= (t - x - j * h) / (i - j) / h;
			f += s * y[i];
		}
		return f;
	}

	/**
	 * 一元全区间等距插值 全部节点
	 * 用拉格朗日插值公式计算指定点处函数近似值
	 * @param x	记录n个节点最左边节点x0=a的数值
	 * @param h	记录节点间距(b-a)/n
	 * @param n	全部节点的个数为n+1
	 * @param y	记录已知n+1个节点的函数值
	 * @param t	待求插值点
	 * @return	t处的函数近似值
	 */
	public static double ee_lagrange2(double x, double h, int n, double[] y, double t) {
		int i, j;
		double s, g = 0;
		for (i = 0; i < n; i++) {
			s = 1;
			for (j = 0; j < i; j++)
				s *= (t - x - j * h) / (i - j) / h;
			for (j = i + 1; j < n; j++)
				s *= (t - x - j * h) / (i - j) / h;
			g += s * y[i];
		}
		return g;
	}
	/**
	 * 一元三点不等距插值
	 * 用三节点抛物线公式计算指定点处函数近似值
	 * @param x	 记录n个节点
	 * @param y	记录已知n个节点的函数值 
	 * @param t	待求插值点
	 * @return	t处的函数近似值
	 */
	public static double en_lagrange3(double[] x, double[] y, double t) {
		int i, j, n = x.length, k, l;
		double s, f;
		if (t < x[0])
			return (y[0] + (y[1] - y[0]) / (x[1] - x[0]) * (t - x[0]));
		if (t > x[n - 1])
			return (y[n - 1] + (y[n - 1] - y[n - 2]) / (x[n - 1] - x[n - 2])
					* (t - x[n - 1]));
		for (i = 0; i < n; i++) {
			if (t == x[i])
				return y[i];
			if (t < x[i])
				break;
		}
		if (i == 1) {
			k = 0;
			l = 3;
		} else if (i == n - 1) {
			k = n - 3;
			l = n;
		} else {
			if ((x[i] - t) > (t - x[i - 1])) {
				k = i - 1;
				l = i + 2;
			} else {
				k = i - 2;
				l = i + 1;
			}
		}
		f = 0;
		for (i = k; i < l; i++) {
			s = 1;
			for (j = k; j < i; j++)
				s *= (t - x[j]) / (x[i] - x[j]);
			for (j = i + 1; j < l; j++)
				s *= (t - x[j]) / (x[i] - x[j]);
			f += s * y[i];
		}
		return f;
	}

	/**
	 * 一元三点等距插值 
	 * 用三节点抛物线插值公式计算指定点处函数近似值
	 * @param x	记录n个节点最左边节点x0=a的数值
	 * @param h	记录节点间距(b-a)/n
	 * @param n	全部节点的个数为n+1
	 * @param y	记录已知n+1个节点的函数值
	 * @param t	待求插值点
	 * @return	t处的函数近似值
	 */
	public static double eelg3(double x, double h, int n, double[] y, double t) {
		int i, j, k, l;
		double s, f;
		if (t < x)
			return (y[0] + (y[1] - y[0]) / h * (t - x));
		if (t > x + n * h - h)
			return (y[n - 1] + (y[n - 1] - y[n - 2]) / h * (t - x - n * h + h));
		for (i = 0; i < n; i++) {
			if (t == x + i * h)
				return y[i];
			if (t < x + i * h)
				break;
		}
		if (i == 1) {
			k = 0;
			l = 3;
		} else if (i == n - 1) {
			k = n - 3;
			l = n;
		} else {
			if (t >= x + (i - 0.5) * h) {
				k = i - 1;
				l = i + 2;
			} else {
				k = i - 2;
				l = i + 1;
			}
		}
		f = 0;
		for (i = k; i < l; i++) {
			s = 1;
			for (j = k; j < i; j++)
				s *= (t - x - j * h) / (i - j) / h;
			for (j = i + 1; j < l; j++)
				s *= (t - x - j * h) / (i - j) / h;
			f += s * y[i];
		}
		return f;
	}

	/**
	 * 连分式不等距插值公式
	 * 用连分公式计算指定点处函数近似值
	 * @param x	 记录n个节点
	 * @param y	记录已知n个节点的函数值 
	 * @param t	待求插值点
	 * @return	t处的函数近似值
	 */
	public double en_pqs(double[] x, double[] y, double t) {
		int i, j, k, l, n = x.length;
		double[] b = new double[n];
		double f;
		b[0] = y[0];
		for (i = 1; i < n; i++) {
			b[i] = y[i];
			for (j = 0; j < i; j++)
				b[i] = (x[i] - x[j]) / (b[i] - b[j]);
		}
		f = b[n - 1];
		for (i = n - 2; i >= 0; i--)
			f = b[i] + (t - x[i]) / f;
		return f;
	}

	/**
	 * 连分式插值 
	 * 用连分插值公式计算指定点处函数近似值
	 * @param x	记录n个节点最左边节点x0=a的数值
	 * @param h	记录节点间距(b-a)/n
	 * @param n	全部节点的个数为n+1
	 * @param y	记录已知n+1个节点的函数值
	 * @param t	待求插值点
	 * @return	t处的函数近似值
	 */
	public static double ee_pqs(double x, double h, int n, double[] y, double t) {
		int i, j, k, l;
		double[] b = new double[n];
		double f;
		b[0] = y[0];
		for (i = 1; i < n; i++) {
			b[i] = y[i];
			for (j = 0; j < i; j++)
				b[i] = (i - j) * h / (b[i] - b[j]);
		}
		f = b[n - 1];
		for (i = n - 2; i >= 0; i--)
			f = b[i] + (t - x - i * h) / f;
		return f;
	}

	/**
	 * 埃尔米特不等距插值公式
	 * @param x		记录n个节点
	 * @param y		记录已知n个节点的函数值
	 * @param dy	记录已知n个节点的一阶导数值
	 * @param t		待求插值点
	 * @return		t处的函数近似值
	 */
	public static double en_hermit(double[] x, double[] y, double[] dy, double t) {
		int i, j, n = x.length;
		double f, l, dl;
		f = 0;
		for (i = 0; i < n; i++) {
			l = 1;
			dl = 0;
			for (j = 0; j < i; j++) {
				l *= (t - x[j]) / (x[i] - x[j]);
				dl += 1 / (x[i] - x[j]);
			}
			for (j = i + 1; j < n; j++) {
				l *= (t - x[j]) / (x[i] - x[j]);
				dl += 1 / (x[i] - x[j]);
			}
			f += (y[i] + (t - x[i]) * (dy[i] - 2 * y[i] * dl)) * l * l;
		}
		return f;
	}

	/**
	 * 用埃尔米特插值公式
	 * 
	 * @param x		记录n个节点最左边节点处x1的数字
	 * @param h		记录节点间距
	 * @param n		全部节点的个数为n
	 * @param y		记录已知n个节点的函数值
	 * @param dy	记录已知n个节点的一阶导数值
	 * @param t		待求插值点
	 * @return		t处的函数近似值
	 */
	public static double ee_hermit(double x, double h, int n, double[] y,
			double[] dy, double t) {
		int i, j;
		double f, l, dl;
		f = 0;
		for (i = 0; i < n; i++) {
			l = 1;
			dl = 0;
			for (j = 0; j < i; j++) {
				l *= (t - x - j * h) / (i - j) / h;
				dl += 1.0 / (i - j) / h;
			}
			for (j = i + 1; j < n; j++) {
				l *= (t - x - j * h) / (i - j) / h;
				dl += 1.0 / (i - j) / h;
			}
			f += (y[i] + (t - x - i * h) * (dy[i] - 2 * y[i] * dl)) * l * l;
		}
		return f;
	}
	/**
	 * 使用埃特金迭代不等距插值法求函数近似值
	 * @param x		节点
	 * @param y		节点函数值
	 * @param t		待求插值点
	 * @param eps	给定精度要求
	 * @return		t处的函数近似值
	 */
	public static double en_aitken(double[] x, double[] y, double t, double eps) {
		int i, j, k, n = x.length;
		double f, l, dl;
		double[] p = new double[n];
		for (i = 0; i < n - 1; i++) {
			k = i;
			for (j = i + 1; j < n; j++)
				if (Math.abs(t - x[j]) < Math.abs(t - x[k]))
					k = j;
			if (k != i) {
				f = x[k];
				x[k] = x[i];
				x[i] = f;
				f = y[k];
				y[k] = y[i];
				y[i] = f;
			}
		}
		p[0] = y[0];
		i = 0;
		while (++i < n) {
			p[i] = y[i];
			for (j = 0; j < i; j++)
				p[i] = ((t - x[j]) * p[i] - (t - x[i]) * p[j]) / (x[i] - x[j]);
			if (Math.abs(p[i] - p[i - 1]) < eps)
				break;
		}
		return p[i];
	}

	/**
	 * 使用AITKEN迭代插值法求函数近似值
	 * @param x0	记节点中最小值
	 * @param h		记节点间距
	 * @param n		节点数
	 * @param y		节点函数值
	 * @param t		待求插值点
	 * @param eps	给定精度要求
	 * @return		t处函数值
	 */
	public static double ee_aitken(double x0, double h, int n, double[] y,
			double t, double eps) {
		int i, j, k, l;
		double[] p = new double[n];
		int[] s = new int[n];
		i = (int) ((t - x0) / h) + 1;
		if (i <= 0)
			for (j = 0; j < n; j++)
				s[j] = j;
		else if (i >= n - 1)
			for (j = 0; j < n; j++)
				s[j] = n - 1 - j;
		else if (t >= x0 + i * h - 0.5 * h) {
			s[0] = i;
			k = i + 1;
			l = i - 1;
			j = 1;
			while (j < n) {
				if (l >= 0)
					s[j++] = l--;
				if (k < n)
					s[j++] = k++;
			}
		} else {
			s[0] = i - 1;
			l = i - 2;
			k = i;
			j = 1;
			while (j < n) {
				if (k < n)
					s[j++] = k++;
				if (l >= 0)
					s[j++] = l--;
			}
		}
		p[0] = y[s[0]];
		i = 0;
		while (++i < n) {
			p[i] = y[s[i]];
			for (j = 0; j < i; j++)
				p[i] = ((t - x0 - s[j] * h) * p[i] - (t - x0 - s[i] * h) * p[j])
						/ (s[i] - s[j]) / h;
			if (Math.abs(p[i] - p[i - 1]) < eps)
				break;
		}
		return p[i];
	}
	/**
	 * 采用光滑不等距插值公式计算 
	 * @param x--插值数据
	 * @param y--插值数据 
	 * @param s--记t所在区间的插值函数的系数S10 S11 S12 
	 * @param t--待求插值点
	 * @return 所求函数值
	 */
	public static double en_spline(double[] x, double[] y, double t, double[] s) {
		int i, j, k, n = x.length;
		double g0, g1, t0, t1, t2, t3;
		double[] u = new double[n + 3];
		for (k = 0; k < n - 1; k++)
			u[k + 2] = (y[k + 1] - y[k]) / (x[k + 1] - x[k]);
		u[1] = 2 * u[2] - u[3];
		u[0] = 2 * u[1] - u[2];
		u[n + 1] = 2 * u[n] - u[n - 1];
		u[n + 2] = 2 * u[n + 1] - u[n];
		for (k = 0; k < n - 1; k++)
			if (t < x[k + 1])
				break;
		s[0] = Math.abs(u[k + 1] - u[k]);
		s[1] = Math.abs(u[k + 2] - u[k + 1]);
		s[2] = Math.abs(u[k + 3] - u[k + 2]);
		s[3] = Math.abs(u[k + 4] - u[k + 3]);
		if (s[0] + s[2] < 1e-12)
			g0 = (u[k + 1] + u[k + 2]) / 2;
		else
			g0 = (s[2] * u[k + 1] + s[0] * u[k + 2]) / (s[2] + s[0]);
		if (s[1] + s[3] < 1e-12)
			g1 = (u[k + 2] + u[k + 3]) / 2;
		else
			g1 = (s[3] * u[k + 2] + s[1] * u[k + 3]) / (s[3] + s[1]);
		s[0] = y[k];
		s[1] = g0;
		s[2] = (3 * u[k + 2] - 2 * g0 - g1) / (x[k + 1] - x[k]);
		s[3] = (g1 + g0 - 2 * u[k + 2]) / (x[k + 1] - x[k]) / (x[k + 1] - x[k]);
		g0 = t - x[k];
		g1 = ((s[3] * g0 + s[2]) * g0 + s[1]) * g0 + s[0];
		return g1;
	}
	/**
	 * 采用全等距光滑插值公式计算 
	 * @param x --数据点中最左边树脂 
	 * @param h --记录节点间距（b-a)/n 
	 * @param y --记录各节点处函数值
	 * @param t --待求值
	 * @param s--记t所在区间的插值函数的系数S10 S11 S12 
	 * @return 所求函数值
	 */
	public static double ee_spline(double x, double h, int n, double[] y, double t,
			double[] s) {
		int k;
		double g0, g1;
		double[] u = new double[n + 3];
		for (k = 0; k < n - 1; k++)
			u[k + 2] = (y[k + 1] - y[k]) / h;
		u[1] = 2 * u[2] - u[3];
		u[0] = 2 * u[1] - u[2];
		u[n + 1] = 2 * u[n] - u[n - 1];
		u[n + 2] = 2 * u[n + 1] - u[n];
		for (k = 0; k < n - 1; k++)
			if (t < x + k * h + h)
				break;
		s[0] = Math.abs(u[k + 1] - u[k]);
		s[1] = Math.abs(u[k + 2] - u[k + 1]);
		s[2] = Math.abs(u[k + 3] - u[k + 2]);
		s[3] = Math.abs(u[k + 4] - u[k + 3]);
		if (s[0] + s[2] < 1e-12)
			g0 = (u[k + 1] + u[k + 2]) / 2;
		else
			g0 = (s[2] * u[k + 1] + s[0] * u[k + 2]) / (s[2] + s[0]);
		if (s[1] + s[3] < 1e-12)
			g1 = (u[k + 2] + u[k + 3]) / 2;
		else
			g1 = (s[3] * u[k + 2] + s[1] * u[k + 3]) / (s[3] + s[1]);
		s[0] = y[k];
		s[1] = g0;
		s[2] = (3 * u[k + 2] - 2 * g0 - g1) / h;
		s[3] = (g1 + g0 - 2 * u[k + 2]) / h / h;
		g0 = t - x - k * h;
		g1 = ((s[3] * g0 + s[2]) * g0 + s[1]) * g0 + s[0];
		return g1;
	}
	/**
	 * 第一种边界条件的三次样条插值函数
	 * @param x 节点值
	 * @param y 节点处函数值
	 * @param dy 节点处的导数值,最初dy[0]=y0'dy[n-1]=yn-1'
	 * @param ddy 记节点处的二阶导数值
	 * @param t 欲求插点值序列
	 * @param z 记插值
	 * @param dz 记插值点点处的导数值
	 * @param ddz 记插值点处的二阶导数值
	 * @return 近似积分值
	 */
	public double spline1(double[] x, double[] y, double[] dy, double[] ddy,
			double[] t, double[] z, double[] dz, double[] ddz) {
		int i, j, n = x.length;
		double[] a = new double[n - 1];
		double[] b = new double[n - 1];
		double alpha, beta, h, h1, h2;
		a[0] = 0;
		b[0] = dy[0];
		for (i = 1; i < n - 1; i++) {
			alpha = (x[i] - x[i - 1]) / (x[i + 1] - x[i - 1]);
			beta = 3 * ((1 - alpha) * (y[i] - y[i - 1]) / (x[i] - x[i - 1]) + alpha
					* (y[i + 1] - y[i]) / (x[i + 1] - x[i]));
			a[i] = -alpha / (2 + (1 - alpha) * a[i - 1]);
			b[i] = (beta - (1 - alpha) * b[i - 1])
					/ (2 + (1 - alpha) * a[i - 1]);
		}
		for (i = n - 2; i > 0; i--)
			dy[i] = a[i] * dy[i + 1] + b[i];
		for (i = 0; i < n - 1; i++)
			ddy[i] = (6 * (y[i + 1] - y[i]) / (x[i + 1] - x[i]) - 2 * (2 * dy[i] + dy[i + 1]))
					/ (x[i + 1] - x[i]);
		ddy[n - 1] = (6 * (y[n - 2] - y[n - 1]) / (x[n - 1] - x[n - 2]) + 2 * (2 * dy[n - 1] + dy[n - 2]))
				/ (x[n - 1] - x[n - 2]);
		for (j = 0; j < t.length; j++) {
			for (i = 0; i < n - 1; i++)
				if (t[j] < x[i + 1])
					break;
			h = x[i + 1] - x[i];
			h1 = x[i + 1] - t[j];
			h2 = t[j] - x[i];
			z[j] = 0;
			z[j] += (3 - 2 * h1 / h) * h1 * h1 * y[i] / h / h;
			z[j] += (3 - 2 * h2 / h) * h2 * h2 * y[i + 1] / h / h;
			z[j] += (1 - h1 / h) * h1 * h1 * dy[i] / h;
			z[j] -= (1 - h2 / h) * h2 * h2 * dy[i + 1] / h;
			dz[j] = 0;
			dz[j] += 6 * (h1 / h - 1) * h1 * y[i] / h / h;
			dz[j] -= 6 * (h2 / h - 1) * h2 * y[i + 1] / h / h;
			dz[j] += (3 * h1 / h - 2) * h1 / h * dy[i];
			dz[j] += (3 * h2 / h - 2) * h2 / h * dy[i + 1];
			ddz[j] = 0;
			ddz[j] += 6 * (1 - 2 * h1 / h) * y[i] / h / h;
			ddz[j] += 6 * (1 - 2 * h2 / h) * y[i + 1] / h / h;
			ddz[j] += (2 - 6 * h1 / h) * dy[i] / h;
			ddz[j] -= (2 - 6 * h2 / h) * dy[i + 1] / h;
		}
		alpha = 0;
		beta = 0;
		for (i = 0; i < n - 1; i++) {
			h = x[i + 1] - x[i];
			alpha += h * (y[i] + y[i + 1]);
			beta = h * h * h * (ddy[i] + ddy[i + 1]);
		}
		return (alpha / 2 - beta / 24);
	}
	/**
	 * 第二种边界条件的样条插值
	 * 由已知节点值，对应函数值和两端点导数值利用三次样条函数计算出各节点处的一阶，二阶导数，计算在
	 * 两端点上的近似积分值。并组成计算插值和微商
	 * @param x 	记节点
	 * @param y 	节点函数值
	 * @param dy 	节点处的导数值
	 * @param ddy 	节点处的二阶导数值
	 * @param t 	欲求插值点序列
	 * @param z		记插值
	 * @param dz	记插值点点处的导数值
	 * @param ddz	记插值点处的二阶导数值
	 * @return		近似积分值
	 */
	public static double spline2(double[] x, double[] y, double[] dy, double[] ddy,
			double[] t, double[] z, double[] dz, double[] ddz) {
		int i, j, n = x.length;
		double[] a = new double[n - 1];
		double[] b = new double[n - 1];
		double alpha, beta, h, h1, h2;
		a[0] = -0.5;
		b[0] = 1.5 * (y[1] - y[0]) / (x[1] - x[0]) - ddy[0] * (x[1] - x[0]) / 4;
		for (i = 1; i < n - 1; i++) {
			alpha = (x[i] - x[i - 1]) / (x[i + 1] - x[i - 1]);
			beta = 3 * ((1 - alpha) * (y[i] - y[i - 1]) / (x[i] - x[i - 1]) + alpha
					* (y[i + 1] - y[i]) / (x[i + 1] - x[i]));
			a[i] = -alpha / (2 + (1 - alpha) * a[i - 1]);
			b[i] = (beta - (1 - alpha) * b[i - 1])
					/ (2 + (1 - alpha) * a[i - 1]);
		}
		dy[n - 1] = (3 * (y[n - 1] - y[n - 2]) / (x[n - 1] - x[n - 2])
				+ ddy[n - 1] * (x[n - 1] - x[n - 2]) / 2 - b[n - 2])
				/ (2 + a[n - 2]);
		for (i = n - 2; i >= 0; i--)
			dy[i] = a[i] * dy[i + 1] + b[i];
		for (i = 1; i < n - 1; i++)
			ddy[i] = (6 * (y[i + 1] - y[i]) / (x[i + 1] - x[i]) - 2 * (2 * dy[i] + dy[i + 1]))
					/ (x[i + 1] - x[i]);

		for (j = 0; j < t.length; j++) {
			for (i = 0; i < n - 1; i++)
				if (t[j] < x[i + 1])
					break;
			h = x[i + 1] - x[i];
			h1 = x[i + 1] - t[j];
			h2 = t[j] - x[i];
			z[j] = 0;
			z[j] += (3 - 2 * h1 / h) * h1 * h1 * y[i] / h / h;
			z[j] += (3 - 2 * h2 / h) * h2 * h2 * y[i + 1] / h / h;
			z[j] += (1 - h1 / h) * h1 * h1 * dy[i] / h;
			z[j] -= (1 - h2 / h) * h2 * h2 * dy[i + 1] / h;
			dz[j] = 0;
			dz[j] += 6 * (h1 / h - 1) * h1 * y[i] / h / h;
			dz[j] -= 6 * (h2 / h - 1) * h2 * y[i + 1] / h / h;
			dz[j] += (3 * h1 / h - 2) * h1 / h * dy[i];
			dz[j] += (3 * h2 / h - 2) * h2 / h * dy[i + 1];
			ddz[j] = 0;
			ddz[j] += 6 * (1 - 2 * h1 / h) * y[i] / h / h;
			ddz[j] += 6 * (1 - 2 * h2 / h) * y[i + 1] / h / h;
			ddz[j] += (2 - 6 * h1 / h) * dy[i] / h;
			ddz[j] -= (2 - 6 * h2 / h) * dy[i + 1] / h;
		}
		alpha = 0;
		beta = 0;
		for (i = 0; i < n - 1; i++) {
			h = x[i + 1] - x[i];
			alpha += h * (y[i] + y[i + 1]);
			beta = h * h * h * (ddy[i] + ddy[i + 1]);
		}
		return (alpha / 2 - beta / 24);
	}
	/**
	 * 第三种边界条件的三次样条函数插值 微商与积分
	 * 计算满足第三类边界条件的各节点处的一阶，二阶导数，计算在两端点上的近似积分值，并成祖计算插值和微商
	 * @param x		记节点
	 * @param y		节点处函数值
	 * @param dy	记节点处的导数值
	 * @param ddy	记节点处的二阶导数值
	 * @param t		欲求插值点序列
	 * @param z		记插值
	 * @param dz	记插值点点处的导数值
	 * @param ddz	记插值点处的二阶导数值
	 * @return		近似积分值
	 */
	public static double spline3(double[] x, double[] y, double[] dy, double[] ddy,
			double[] t, double[] z, double[] dz, double[] ddz) {
		int i, j, n = x.length;
		double[] a = new double[n - 1];
		double[] b = new double[n - 1];
		double[] c = new double[n - 1];
		double[] u = new double[n];
		double[] v = new double[n];
		double alpha = 0, alpha1 = 0, beta = 0, beta1 = 0, h, h1, h2;
		a[0] = 0;
		b[0] = 1;
		c[1] = 0;
		alpha1 = (x[n - 1] - x[n - 2]) / (x[n - 1] - x[n - 2] + x[1] - x[0]);
		beta1 = 3 * ((1 - alpha1) * (y[n - 1] - y[n - 2])
				/ (x[n - 1] - x[n - 2]) + alpha1 * (y[1] - y[0])
				/ (x[1] - x[0]));
		for (i = 1; i < n - 1; i++) {
			h1 = x[i] - x[i - 1];
			h2 = x[i + 1] - x[i];
			alpha = h1 / (h1 + h2);
			;
			beta = 3 * ((1 - alpha) * (y[i] - y[i - 1]) / h1 + alpha
					* (y[i + 1] - y[i]) / h2);
			h = 2 + (1 - alpha1) * a[i - 1];
			a[i] = -alpha1 / h;
			b[i] = -(1 - alpha1) * b[i - 1] / h;
			c[i] = (beta1 - (1 - alpha1) * c[i - 1]) / h;
			alpha1 = alpha;
			beta1 = beta;
		}
		u[n - 1] = 1;
		v[n - 1] = 0;
		for (i = n - 2; i > 0; i--) {
			u[i] = a[i] * u[i + 1] + b[i];
			v[i] = a[i] * v[i + 1] + c[i];
		}
		dy[n - 2] = (beta - alpha * v[1] - (1 - alpha) * v[n - 2])
				/ (2 + alpha * u[1] + (1 - alpha) * u[n - 2]);
		for (i = 0; i < n - 2; i++)
			dy[i] = u[i + 1] * dy[n - 2] + v[i + 1];
		dy[n - 1] = dy[0];
		for (i = 0; i < n - 1; i++)
			ddy[i] = (6 * (y[i + 1] - y[i]) / (x[i + 1] - x[i]) - 2 * (2 * dy[i] + dy[i + 1]))
					/ (x[i + 1] - x[i]);
		ddy[n - 1] = ddy[0];

		for (j = 0; j < t.length; j++) {
			for (i = 0; i < n - 1; i++)
				if (t[j] < x[i + 1])
					break;
			h = x[i + 1] - x[i];
			h1 = x[i + 1] - t[j];
			h2 = t[j] - x[i];
			z[j] = 0;
			z[j] += (3 - 2 * h1 / h) * h1 * h1 * y[i] / h / h;
			z[j] += (3 - 2 * h2 / h) * h2 * h2 * y[i + 1] / h / h;
			z[j] += (1 - h1 / h) * h1 * h1 * dy[i] / h;
			z[j] -= (1 - h2 / h) * h2 * h2 * dy[i + 1] / h;
			dz[j] = 0;
			dz[j] += 6 * (h1 / h - 1) * h1 * y[i] / h / h;
			dz[j] -= 6 * (h2 / h - 1) * h2 * y[i + 1] / h / h;
			dz[j] += (3 * h1 / h - 2) * h1 / h * dy[i];
			dz[j] += (3 * h2 / h - 2) * h2 / h * dy[i + 1];
			ddz[j] = 0;
			ddz[j] += 6 * (1 - 2 * h1 / h) * y[i] / h / h;
			ddz[j] += 6 * (1 - 2 * h2 / h) * y[i + 1] / h / h;
			ddz[j] += (2 - 6 * h1 / h) * dy[i] / h;
			ddz[j] -= (2 - 6 * h2 / h) * dy[i + 1] / h;
		}
		alpha = 0;
		beta = 0;
		for (i = 0; i < n - 1; i++) {
			h = x[i + 1] - x[i];
			alpha += h * (y[i] + y[i + 1]);
			beta = h * h * h * (ddy[i] + ddy[i + 1]);
		}
		return (alpha / 2 - beta / 24);
	}

}
