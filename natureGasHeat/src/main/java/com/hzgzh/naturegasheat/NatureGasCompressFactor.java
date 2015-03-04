package com.hzgzh.naturegasheat;

public class NatureGasCompressFactor {
    // !=======================================================================
    double K1, CNS[] = new double[59], BI[] = new double[19];
    ;
    double MWX, RGAS, TCM, DCM;
    double FI[] = new double[22], SI[] = new double[22], WI[] = new double[22];
    double EIJ[][] = new double[22][22], UIJ[][] = new double[22][22],
            KIJ[][] = new double[22][22], GIJ[][] = new double[22][22];
    double A[] = {0, 0.153832600e0, 1.341953000e0, -2.998583000e0,
            -0.048312280e0, 0.375796500e0, -1.589575000e0, -0.053588470e0,
            0.886594630e0, -0.710237040e0, -1.471722000e0, 1.321850350e0,
            -0.786659250e0, 0.2291290e-08, 0.157672400e0, -0.436386400e0,
            -0.044081590e0, -0.003433888e0, 0.032059050e0, 0.024873550e0,
            0.073322790e0, -0.001600573e0, 0.642470600e0, -0.416260100e0,
            -0.066899570e0, 0.279179500e0, -0.696605100e0, -0.002860589e0,
            -0.008098836e0, 3.150547000e0, 0.007224479e0, -0.705752900e0,
            0.534979200e0, -0.079314910e0, -1.418465000e0, -0.5999050e-16,
            0.105840200e0, 0.034317290e0, -0.007022847e0, 0.024955870e0,
            0.042968180e0, 0.746545300e0, -0.291961300e0, 7.294616000e0,
            -9.936757000e0, -0.005399808e0, -0.243256700e0, 0.049870160e0,
            0.003733797e0, 1.874951000e0, 0.002168144e0, -0.658716400e0,
            0.000205518e0, 0.009776195e0, -0.020487080e0, 0.015573220e0,
            0.006862415e0, -0.001226752e0, 0.002850908e0};
    double B[] = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2,
            2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4,
            4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 7, 7, 8, 8, 8, 9, 9};
    double C[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0,
            0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1,
            1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1};

    // DATA=======================================================================
    double K[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 2, 2, 4, 4, 0,
            0, 2, 2, 2, 4, 4, 4, 4, 0, 1, 1, 2, 2, 3, 3, 4, 4, 4, 0, 0, 2, 2,
            2, 4, 4, 0, 2, 2, 4, 4, 0, 2, 0, 2, 1, 2, 2, 2, 2};
    double G[] = {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0};

    // COMMON /GRENZDATA/ XN,XH
    // !.....Equation of state parameters
    double Q[] = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
            1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1};
    double F[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    double S[] = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    double W[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    double U[] = {0, 0, 0.5e0, 1, 3.5e0, -0.5e0, 4.5e0, 0.5e0, 7.5e0, 9.5e0,
            6, 12, 12.5e0, -6, 2, 3, 2, 2, 11, -0.5e0, 0.5e0, 0, 4, 6, 21, 23,
            22, -1, -0.5e0, 7, -1, 6, 4, 1, 9, -13, 21, 8, -0.5e0, 0, 2, 7, 9,
            22, 23, 1, 9, 3, 8, 23, 1.5e0, 5, -0.5e0, 4, 7, 3, 0, 1, 0};
    // .....CharacterizationP arameters
    double[] MW = {0, 16.0430e0, 28.0135e0, 44.0100e0, 30.0700e0, 44.0970e0,
            18.0153e0, 34.0820e0, 2.0159e0, 28.0100e0, 31.9988e0, 58.1230e0,
            58.1230e0, 72.1500e0, 72.1500e0, 86.1770e0, 100.2040e0, 114.2310e0,
            128.2580e0, 142.2850e0, 4.0026e0, 39.9480e0};
    double[] EI = {0, 151.318300e0, 99.737780e0, 241.960600e0, 244.166700e0,
            298.118300e0, 514.015600e0, 296.355000e0, 26.957940e0,
            105.534800e0, 122.766700e0, 324.068900e0, 337.638900e0,
            365.599900e0, 370.682300e0, 402.636293e0, 427.722630e0,
            450.325022e0, 470.840891e0, 489.558373e0, 2.610111e0, 119.629900e0};
    double[] KI = {0, 0.4619255e0, 0.4479153e0, 0.4557489e0, 0.5279209e0,
            0.5837490e0, 0.3825868e0, 0.4618263e0, 0.3514916e0, 0.4533894e0,
            0.4186954e0, 0.6406937e0, 0.6341423e0, 0.6738577e0, 0.6798307e0,
            0.7175118e0, 0.7525189e0, 0.7849550e0, 0.8152731e0, 0.8437826e0,
            0.3589888e0, 0.4216551e0};
    double[] GI = {0, 0, 0.027815e0, 0.189065e0, 0.079300e0, 0.141239e0,
            0.332500e0, 0.088500e0, 0.034369e0, 0.038953e0, 0.021000e0,
            0.256692e0, 0.281835e0, 0.332267e0, 0.366911e0, 0.289731e0,
            0.337542e0, 0.383381e0, 0.427354e0, 0.469659e0, 0, 0};
    double[] QI = {0, 0, 0, 0.69e0, 0, 0, 1.06775e0, 0.633276e0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    double XN[] = {0, 50.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    double XH[] = {0, 100.0, 20.0, 5.0, 1.5, 1.5, 0.5, 0.5, 0.1, 0.05, 0.05,
            0.05, 0.05, 30.0, 50.0, 0.02, 0.5, 0.015, 0.02, 0.02, 10.0, 3.0};

    void calc(double[] x, double p, double t) {
        double d = 0, z = 0, bmix = 0;
        Data data = new Data(p, t, d, z, bmix);
        DCAGA(x);
        DZOFPT(data);
        PZOFDT(data);
    }

    void DCAGA(double XJ[]) {
        int I, J, N;
        double SUM, XI[] = new double[22];
        double U1, G1, Q1, F1, E1;
        double XIJ, EIJ0, GIJ0, BN;
        XI[1] = XJ[1];
        XI[4] = XJ[2];
        XI[5] = XJ[3];
        XI[11] = XJ[4];
        XI[12] = XJ[5];
        XI[13] = XJ[6];
        XI[14] = XJ[7];
        XI[15] = XJ[8];
        XI[16] = XJ[9];
        XI[17] = XJ[10];
        XI[18] = XJ[11];
        XI[19] = XJ[12];
        XI[3] = XJ[13];
        XI[2] = XJ[14];
        XI[7] = XJ[15];
        XI[20] = XJ[16];
        XI[6] = XJ[17];
        XI[10] = XJ[18];
        XI[21] = XJ[19];
        XI[8] = XJ[20];
        XI[9] = XJ[21];
        // Normalize mole fractions
        SUM = 0;
        MWX = 0;

        for (I = 1; I <= 21; I++)
            SUM = SUM + XI[I];

        for (I = 1; I <= 21; I++)
            XI[I] = XI[I] / SUM;
        // !.calculate molecular weight
        RGAS = 8.31451D - 3;
        MWX = 0;

        for (I = 1; I <= 21; I++)
            MWX = MWX + XI[I] * MW[I];
        for (N = 1; N <= 18; N++)
            BI[N] = 0;
        K1 = 0;
        U1 = 0;
        G1 = 0;
        Q1 = 0;
        F1 = 0;
        E1 = 0;

        for (I = 1; I <= 21; I++) {
            K1 = K1 + XI[I] * Math.pow(KI[I], 2.5e0);
            U1 = U1 + XI[I] * Math.pow(EI[I], 2.5e0);
            G1 = G1 + XI[I] * GI[I];
            Q1 = Q1 + XI[I] * QI[I];
            F1 = F1 + XI[I] * XI[I] * FI[I];
            E1 = E1 + XI[I] * EI[I];
        }

        TCM = 1.261 * E1;
        DCM = Math.pow(K1, -1.2e0);
        K1 = K1 * K1;
        U1 = U1 * U1;
        for (I = 1; I <= 8; I++)
            for (J = I + 1; J <= 19; J++) {
                XIJ = XI[I] * XI[J];
                if (XIJ != 0) {
                    K1 = K1 + 2.e0 * XIJ * (Math.pow(KIJ[I][J], 5.e0) - 1.e0)
                            * Math.pow(KI[I] * KI[J], 2.5e0);
                    U1 = U1 + 2.e0 * XIJ * (Math.pow(UIJ[I][J], 5.e0) - 1.e0)
                            * Math.pow(EI[I] * EI[J], 2.5e0);
                    G1 = G1 + XIJ * (GIJ[I][J] - 1.E0) * (GI[I] + GI[J]);
                }
            }
        for (I = 1; I <= 21; I++)
            for (J = I; J <= 21; J++) {
                XIJ = XI[I] * XI[J];
                if (XIJ != 0) {
                    if (I != J)
                        XIJ = 2.e0 * XIJ;
                    EIJ0 = EIJ[I][J] * Math.sqrt(EI[I] * EI[J]);
                    GIJ0 = GIJ[I][J] * (GI[I] + GI[J]) / 2.e0;

                    for (N = 1; N <= 18; N++) {
                        BN = (GIJ0 + 1.e0 - Math.pow(G[N], G[N]))
                                * Math.pow((QI[I] * QI[J] + 1.e0 - Q[N]), Q[N])
                                * Math.pow(
                                (Math.sqrt(FI[I] * FI[J]) + 1.e0 - F[N]),
                                F[N])
                                * Math.pow((SI[I] * SI[J] + 1.e0 - S[N]), S[N])
                                * Math.pow((WI[I] * WI[J] + 1.e0 - W[N]), W[N]);
                        BI[N] = BI[N] + A[N] * XIJ * Math.pow(EIJ0, U[N])
                                * Math.pow((KI[I] * KI[J]), 1.5e0) * BN;
                    }
                }
            }
        K1 = Math.pow(K1, 0.2e0);
        U1 = Math.pow(U1, 0.2e0);

        for (N = 13; N <= 58; N++)
            CNS[N] = Math.pow((G1 + 1.E0 - G[N]), G[N])
                    * Math.pow((Q1 * Q1 + 1.e0 - Q[N]), Q[N])
                    * Math.pow((F1 + 1.e0 - F[N]), F[N]) * A[N]
                    * Math.pow(U1, U[N]);
    }

    void PZOFDT(Data data) {

        int N;
        double D, T, P, Z, BMIX, DR;
        D = data.D;
        T = data.T;
        P = data.P;
        Z = data.Z;
        BMIX = data.BMIX;

        DR = D * Math.pow(K1, 3);
        BMIX = 0;

        for (N = 1; N <= 18; N++)
            BMIX = BMIX + BI[N] / Math.pow(T, U[N]);
        Z = 1.e0 + BMIX * D;

        for (N = 13; N <= 18; N++)
            Z = Z - DR * CNS[N] / Math.pow(T, U[N]);

        for (N = 13; N <= 58; N++)
            Z = Z + CNS[N] / Math.pow(T, U[N])
                    * (B[N] - C[N] * K[N] * Math.pow(DR, K[N]))
                    * Math.pow(DR, B[N]) * Math.exp(-C[N] * Math.pow(DR, K[N]));
        P = D * RGAS * T * Z;
        data.P = P;
        data.D = D;
        data.Z = Z;
        data.T = T;
        data.BMIX = BMIX;
    }

    // =======================================================================
    void DZOFPT(Data data) {
        double P, T, D, Z, BMIX;
        double X1, X2, X3, F = 0, F1 = 0, F2 = 0, F3 = 0, TOL;

        P = data.P;
        D = data.D;
        Z = data.Z;
        T = data.T;
        BMIX = data.BMIX;

        int I;
        TOL = 0.5e-9;
        X1 = 0.000001e0;
        X2 = 40.e0;
        D = 0;

        Data d1 = new Data(X1, T, F1, Z, BMIX);
        Data d2 = new Data(X2, T, F2, Z, BMIX);
        PZOFDT(d1);
        PZOFDT(d2);

        F1 = d1.P - P;
        F2 = d2.P - P;
        if (F1 * F2 > 0)
            return;
        // -----------------------------------------------------------------------
        // BEGIN ITERATING
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // -

        for (I = 1; I <= 60; I++) {
            // . . . Use False Position to get point 3.
            X3 = X1 - F1 * (X2 - X1) / (F2 - F1);
            Data d3 = new Data(X3, data.T, F3, data.Z, data.BMIX);
            PZOFDT(d3);
            F3 = d3.P - P;
            // ...Use points 1, 2, and 3 to estimate the root using chamber's
            // . . .method (quadratic solution) .
            D = X1 * F2 * F3 / ((F1 - F2) * (F1 - F3)) + X2 * F1 * F3
                    / ((F2 - F1) * (F2 - F3)) + X3 * F1 * F2
                    / ((F3 - F1) * (F3 - F2));
            if ((D - X1) * (D - X2) >= 0)
                D = (X1 + X2) / 2.e0;
            Data d = new Data(D, data.T, F, data.Z, data.BMIX);
            PZOFDT(d);
            F = d.P - P;
            if (Math.abs(F) <= TOL)
                return;
            // . . . Discard quadratic solution if false position root is closer
            if (Math.abs(F3) < Math.abs(F) && F * F3 > 0) {
                if (F3 * F1 > 0) {
                    X1 = X3;
                    F1 = F3;
                } else {
                    X2 = X3;
                    F2 = F3;
                }
            } else {
                // . . . Swap in new value from quadratic solution
                if (F * F3 < 0) {
                    X1 = D;
                    F1 = F;
                    X2 = X3;
                    F2 = F3;
                } else {
                    if (F3 * F1 > 0) {
                        X1 = D;
                        F1 = F;
                    } else {
                        X2 = D;
                        F2 = F;
                    }
                }
            }
        }
        D = 0;
        data.P = P;
        data.D = D;
        data.Z = Z;
        data.T = T;
        data.BMIX = BMIX;
    }

    void init() {
        FI[8] = 1;
        SI[6] = 1.5822e0;
        SI[7] = 0.390e0;
        WI[6] = 1;

        for (int i = 0; i < 22; i++)
            for (int j = 0; i < 22; j++) {
                EIJ[i][j] = UIJ[i][j] = KIJ[i][j] = GIJ[i][j] = 1;
            }

        EIJ[1] = new double[]{1, 1, 0.971640e0, 0.960644e0, 1, 0.994635e0,
                0.708218e0, 0.931484e0, 1.170520e0, 0.990126e0, 1, 1.019530e0,
                0.989844e0, 1.002350e0, 0.999268e0, 1.107274e0, 0.880880e0,
                0.880973e0, 0.881067e0, 0.881161e0, 1, 1};
        EIJ[2] = new double[]{1, 1, 1, 1.022740e0, 0.970120e0, 0.945939e0,
                0.746954e0, 0.902271e0, 1.086320e0, 1.005710e0, 1.021000e0,
                0.946914e0, 0.973384e0, 0.959340e0, 0.945520e0, 1, 1, 1, 1, 1,
                1, 1};
        EIJ[3] = new double[]{1, 1, 1, 1, 0.925053e0, 0.960237e0, 0.849408e0,
                0.955052e0, 1.281790e0, 1.5e0, 1, 0.906849e0, 0.897362e0,
                0.726255e0, 0.859764e0, 0.855134e0, 0.831229e0, 0.808310e0,
                0.786323e0, 0.765171e0, 1, 1};
        EIJ[4] = new double[]{1, 1, 1, 1, 1, 1.022560e0, 0.693168e0,
                0.946871e0, 1.164460e0, 1, 1, 1, 1.013060e0, 1, 1.00532e0, 1,
                1, 1, 1, 1, 1, 1};
        EIJ[5] = new double[]{1, 1, 1, 1, 1, 1, 1, 1, 1.034787e0, 1, 1, 1,
                1.0049e0, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        EIJ[7] = new double[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1.008692e0, 1.010126e0, 1.011501e0, 1.012821e0, 1.014089e0, 1,
                1};
        EIJ[8] = new double[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1.1e0, 1, 1.3e0,
                1.3e0, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        UIJ[1] = new double[]{1, 1, 0.886106e0, 0.963827e0, 1, 0.990877e0, 1,
                0.736833e0, 1.156390e0, 1, 1, 1, 0.992291e0, 1, 1.003670e0,
                1.302576e0, 1.191904e0, 1.205769e0, 1.219634e0, 1.233498e0, 1,
                1};
        UIJ[2] = new double[]{1, 1, 1, 0.835058e0, 0.816431e0, 0.915502e0, 1,
                0.993476e0, 0.408838e0, 1, 1, 1, 0.993556e0, 1, 1, 1, 1, 1, 1,
                1, 1, 1};
        UIJ[3] = new double[]{1, 1, 1, 1, 0.969870e0, 1, 1, 1.045290e0, 1,
                0.9e0, 1, 1, 1, 1, 1, 1.066638e0, 1.077634e0, 1.088178e0,
                1.098291e0, 1.108021e0, 1, 1};
        UIJ[4] = new double[]{1, 1, 1, 1, 1, 1.065173e0, 1, 0.971926e0,
                1.616660e0, 1, 1, 1.25e0, 1.25e0, 1.25e0, 1.25e0, 1, 1, 1, 1,
                1, 1, 1};
        UIJ[7] = new double[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1.028973e0, 1.033754e0, 1.038338e0, 1.042735e0, 1.046966e0, 1,
                1};
        KIJ[1] = new double[]{1, 1, 1.003630e0, 0.995933e0, 1, 1.007619e0, 1,
                1.000080e0, 1.023260e0, 3 * 1, 0.997596e0, 1, 1.002529e0,
                0.982962e0, 0.983565e0, 0.982707e0, 0.981849e0, 0.980991e0, 1,
                1};
        KIJ[2] = new double[]{1, 1, 1, 0.982361e0, 1.007960e0, 1, 1,
                0.9425960e0, 1.032270e0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        KIJ[3] = new double[]{1, 1, 1, 1, 1.008510e0, 1, 1, 1.00779e0, 1, 1,
                1, 1, 1, 1, 1, 0.910183e0, 0.895362e0, 0.881152e0, 0.867520e0,
                0.854406e0, 1, 1};
        KIJ[4] = new double[]{1, 1, 1, 1, 1, 0.986893e0, 1, 0.999969e0,
                1.020340e0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        KIJ[7] = new double[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                0.968130e0, 0.962870e0, 0.957828e0, 0.952441e0, 0.948338e0, 1,
                1};

        GIJ[1][3] = 0.807653e0;
        GIJ[1][8] = 1.957310e0;
        GIJ[2][3] = 0.982746e0;
        GIJ[3][4] = 0.370296e0;
        GIJ[3][6] = 1.673090e0;
    }

    class Data {
        double P, D, T, BMIX, Z;

        public Data(double D, double T, double P, double Z, double BMIX) {
            this.P = P;
            this.D = D;
            this.Z = Z;
            this.T = T;
            this.BMIX = BMIX;
        }
    }
}