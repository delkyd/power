package com.example.winsteam.condenser;

public class Condenser_Hei_Standard {
	/**
	 * calculate uncorrected heat transfer coefficence of surface condenser
	 * @param ws			water velocity unit:ft/s
	 * @param TUBE_DIAMETER	Tube Diameter of condenser unit:inch
	 * @return 				uncorrected heat transfer coeff
	 */
	public double getUnCorrectedHeatTransferCoefficents(double ws,		
			double tube_od) {
		ws=this.velSI2US(ws);
		double U = 0;
		if(tube_od>0.624 && tube_od<0.751)
			U = Interpolation.en_lagrange3(WS, U1, ws);
		if(tube_od>0.874 && tube_od<1.01)
			U = Interpolation.en_lagrange3(WS, U2, ws);
		if(tube_od>1.110 && tube_od<1.26)
			U = Interpolation.en_lagrange3(WS, U3, ws);
		if(tube_od>1.360 && tube_od<1.51)
			U = Interpolation.en_lagrange3(WS, U4, ws);
		if(tube_od>1.610 && tube_od<1.76)
			U = Interpolation.en_lagrange3(WS, U5, ws);
		if(tube_od>1.860 && tube_od<2.01)
			U = Interpolation.en_lagrange3(WS, U6, ws);
		return htfUS2SI(U);
	}
	/**
	 * Calculate inlet water changed effect 
	 * @param tw	inlet water temperature F
	 * @return		correct factor
	 */
	public double getInletWaterTempCorrectionFactor(double tw) {
		tw=this.temperatureSI2US(tw);
		return Interpolation.en_lagrange3(TW, FW, tw);
	}

	final int CU_FE_194 = 0;
	final int ARSENICAL_CU = 1;
	final int ADMIRALTY = 2;
	final int AL_BRASS = 3;
	final int AL_BRONZE = 4;
	final int CARBON_STEEL = 5;
	final int CU_NI_90_10 = 6;
	final int CU_NI_70_30 = 7;
	final int SS_UNS_S43035 = 8;
	final int TITANIUM_GRADE = 9;
	final int SS_UNS_S44660 = 10;
	final int SS_UNS_S44735 = 11;
	final int SS_TP_304 = 12;
	final int SS_TP316_317 = 13;
	final int SS_UNS_N08367 = 14;
	
	/**
	 * Calculate the effect of tube material and thickness o
	 * @param material_type	int material name
	 * @param guage			double tube thickness
	 * @return				FM correct factor
	 */
	public double getTubeMaterialGuageCorrectionFactor(int material_type,
			double guage) {
		guage=this.lengthSI2US(guage);
		double fm = 0;
		switch (material_type) {
		case CU_FE_194:
			fm = Interpolation.en_lagrange3(GUAGE, FM_CU_FE_194, guage);
			break;
		case ARSENICAL_CU:
			fm = Interpolation.en_lagrange3(GUAGE, FM_ARSENICAL_CU, guage);
			break;
		case ADMIRALTY:
			fm = Interpolation.en_lagrange3(GUAGE, FM_ADMIRALTY, guage);
			break;
		case AL_BRASS:
			fm = Interpolation.en_lagrange3(GUAGE, FM_AL_BRASS, guage);
			break;
		case AL_BRONZE:
			fm = Interpolation.en_lagrange3(GUAGE, FM_AL_BRONZE, guage);
			break;
		case CARBON_STEEL:
			fm = Interpolation.en_lagrange3(GUAGE, FM_CARBON_STEEL, guage);
			break;
		case CU_NI_90_10:
			fm = Interpolation.en_lagrange3(GUAGE, FM_CU_NI_90_10, guage);
			break;
		case CU_NI_70_30:
			fm = Interpolation.en_lagrange3(GUAGE, FM_CU_NI_70_30, guage);
			break;
		case SS_UNS_S43035:
			fm = Interpolation.en_lagrange3(GUAGE, FM_SS_UNS_S43035, guage);
			break;
		case TITANIUM_GRADE:
			fm = Interpolation.en_lagrange3(GUAGE, FM_TITANIUM_GRADES, guage);
			break;
		case SS_UNS_S44660:
			fm = Interpolation.en_lagrange3(GUAGE, FM_SS_UNS_S44660, guage);
			break;
		case SS_UNS_S44735:
			fm = Interpolation.en_lagrange3(GUAGE, FM_SS_UNS_S44735, guage);
			break;
		case SS_TP_304:
			fm = Interpolation.en_lagrange3(GUAGE, FM_TP_304, guage);
			break;
		case SS_TP316_317:
			fm = Interpolation.en_lagrange3(GUAGE, FM_TP_316_317, guage);
			break;
		case SS_UNS_N08367:
			fm = Interpolation.en_lagrange3(GUAGE, FM_SS_UNS_N08367, guage);
			break;
		default:
			break;

		}
		return fm;
	}
	/**
	 * Calculate corrected heat transfer coefficience ,consider the inlet water temperature,tube thickness,
	 * tube material,water velocity
	 * @param ws
	 * @param tw
	 * @param TUBE_DIAM
	 * @param MATERIAL
	 * @param guage
	 * @return
	 */
	public double getCorrectedHeatTransferCoeffice(double ws, double tw,
			double tube_od, int MATERIAL, double guage) {
		return getUnCorrectedHeatTransferCoefficents(ws, tube_od)
				* getInletWaterTempCorrectionFactor(tw)
				* getTubeMaterialGuageCorrectionFactor(MATERIAL, guage)*mFc;
	}
	/**
	 * data
	 */
	
	double[] WS = { 3, 3.5, 4, 4.5, 5, 5.5, 6, 6.5, 7, 7.5, 8, 8.5, 9, 9.5, 10,
			10.5, 11, 11.5, 12 };
	double[] U1 = { 462.5, 499.5, 534.0, 566.4, 597.0, 626.2, 654.0, 680.7,
			706.4, 731.2, 755.2, 775.5, 795.3, 814.1, 831.9, 848.9, 865.2,
			880.7, 895.6 };
	double[] U2 = { 455.0, 492.0, 526.0, 557.9, 588.1, 616.8, 644.2, 670.5,
			695.8, 720.3, 743.9, 763.9, 783.2, 801.6, 819.0, 835.6, 851.5,
			866.6, 881.1 };
	double[] U3 = { 448.6, 484.5, 518.0, 549.4, 579.1, 607.4, 634.4, 660.3,
			685.2, 709.3, 732.6, 752.0, 770.7, 788.4, 805.3, 821.4, 836.7,
			851.3, 865.3 };
	double[] U4 = { 441.7, 477.1, 510.0, 540.9, 570.2, 598.0, 624.6, 650.1,
			674.7, 698.3, 721.2, 740.4, 758.7, 776.1, 792.6, 808.3, 823.2,
			837.5, 851, 2 };
	double[] U5 = { 434.7, 469.6, 502.0, 532.5, 561, 3, 588.6, 614.8, 639.9,
			664.1, 687.4, 709.9, 727.8, 745.7, 762.7, 778.8, 794.1, 808.8,
			822.7, 836.0 };
	double[] U6 = { 427.8, 462.1, 494.0, 524.0, 552.3, 579.8, 605.0, 629.7,
			653.5, 676.4, 698.6, 716.8, 734.4, 751.0, 766.8, 781.8, 796.2,
			809.8, 822.9 };
	double[] TW = { 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44,
			45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61,
			62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78,
			79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
			96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109,
			110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120 };
	double[] FW = { 0.650, 0.659, 0.669, 0.678, 0.687, 0.796, 0.706, 0.715,
			0.724, 0.733, 0.743, 0.752, 0.761, 0.770, 0.780, 0.789, 0.798,
			0.807, 0.816, 0.825, 0.834, 0.843, 0.852, 0.861, 0.870, 0.879,
			0.888, 0.897, 0.905, 0.914, 0.923, 0.932, 0.941, 0.950, 0.959,
			0.968, 0.975, 0.982, 0.989, 0.994, 1.000, 1.005, 1.010, 1.015,
			1.020, 1.025, 1.029, 1.033, 1.037, 1.041, 1.045, 1.048, 1.051,
			1.054, 1.057, 1.060, 1.063, 1.066, 1.069, 1.072, 1.075, 1.078,
			1.080, 1.083, 1.085, 1.088, 1.090, 1.092, 1.095, 1.097, 1.100,
			1.103, 1.105, 1.108, 1.110, 1.113, 1.115, 1.117, 1.119, 1.121,
			1.123, 1.125, 1.127, 1.129, 1.131, 1.133, 1.135, 1.137, 1.139,
			1.141, 1.143 };
	double[] GUAGE = { 0.02, 0.022, 0.025, 0.028, 0.035, 0.049, 0.065, 0.083,
			0.109 };
	double[] FM_CU_FE_194 = { 1.042, 1.041, 1.039, 1.038, 1.034, 1.028, 1.020,
			1.010, 0.997 };
	double[] FM_ARSENICAL_CU = { 1.038, 1.037, 1.035, 1.033, 1.029, 1.020,
			1.010, 0.997, 0.979 };
	double[] FM_ADMIRALTY = { 1.029, 1.027, 1.024, 1.021, 1.013, 0.998, 0.981,
			0.961, 0.932 };
	double[] FM_AL_BRASS = { 1.027, 1.025, 1.021, 1.018, 1.010, 0.993, 0.974,
			0.952, 0.921 };
	double[] FM_AL_BRONZE = { 1.021, 1.018, 1.014, 1.009, 0.999, 0.979, 0.956,
			0.930, 0.892 };
	double[] FM_CARBON_STEEL = { 1.002, 0.998, 0.990, 0.983, 0.967, 0.936,
			0.901, 0.863, 0.810 };
	double[] FM_CU_NI_90_10 = { 1.000, 0.995, 0.987, 0.980, 0.963, 0.930,
			0.893, 0.854, 0.800 };
	double[] FM_CU_NI_70_30 = { 0.974, 0.967, 0.957, 0.946, 0.922, 0.876,
			0.828, 0.777, 0.710 };
	double[] FM_SS_UNS_S43035 = { 0.959, 0.951, 0.938, 0.926, 0.898, 0.846,
			0.792, 0.736, 0.664 };
	double[] FM_TITANIUM_GRADES = { 0.951, 0.942, 0.928, 0.915, 0.885, 0.830,
			0.772, 0.714, 0.640 };
	double[] FM_SS_UNS_S44660 = { 0.932, 0.922, 0.906, 0.891, 0.857, 0.795,
			0.732, 0.669, 0.591 };
	double[] FM_SS_UNS_S44735 = { 0.928, 0.917, 0.901, 0.886, 0.851, 0.787,
			0.723, 0.659, 0.581 };
	double[] FM_TP_304 = { 0.910, 0.897, 0.879, 0.862, 0.823, 0.754, 0.685,
			0.619, 0.539 };
	double[] FM_TP_316_317 = { 0.904, 0.891, 0.872, 0.854, 0.815, 0.744, 0.674,
			0.607, 0.527 };
	double[] FM_SS_UNS_N08367 = { 0.879, 0.864, 0.843, 0.823, 0.779, 0.702,
			0.628, 0.558, 0.477 };
	
	/**
	 * Condenser Pressure,Ps 
	 */
	public double mPs;
	/**
	 * Condenser Temperature,Ts
	 */
	public double mTs;
	/**
	 * Condenser Heat Duty,Q
	 */
	public double mQ;
	/**
	 * Turbine Exhaust Steam Flow Rate,Ws
	 */
	public double mW_Steam;
	/**
	 * Circulating Water Flow Rate
	 */
	public double mW_Water;
	/**
	 * Circulate Water Inlet Temperature,T1
	 */
	public double mT1;
	/**
	 * Tube Water Velocity,Vw
	 */
	public double mVw;
	/**
	 * Cleanliness Factor,Fc
	 */
	public double mFc=0.8;
	/**
	 * Tube out diameter
	 */
	public double mTubeOutDiam;
	/**
	 * Tube Out.Diameter_num,D
	 */
	public int mDial_Num;
	/**
	 * Tube Inlet.Diameter,D1
	 */
	public double mGuage;
	/**
	 * Circulating Water Density,rho
	 */
	public double mRho;
	/**
	 * Circulating Water Specific Heat,Cp
	 */
	public double mCp;
	/**
	 * Area of Condenser
	 */
	public double mArea;
	/**
	 * Tube Material
	 */
	public int mTubeMaterial;
	/**
	 * Tube length
	 */
	public double mLt;
	/**
	 * Tube Number
	 */
	public int mTubeNumber;
	/**
	 * calculate area of condenser
	 */
	public void calcSurfaceAreaOfCondenser(){
		double LMTD=getLogMeanTerminalDiffirence();
		double U=this.getCorrectedHeatTransferCoeffice(mVw, mT1, mDial_Num, mTubeMaterial, mGuage);
		mArea=mQ/U/LMTD;
	}
	public double getCirculateWaterTemperatureRise(){
		return mQ/(mW_Water*mRho*mCp)+mT1;
	}
	public double getInletTermDiffirence(){
		return mTs-mT1;
	}
	public double getTerminalDifference(){
		return mTs-mQ/(mW_Water*mRho*mCp)-mT1;
	}
	public double getLogMeanTerminalDiffirence(){
		double t2=mQ/(mW_Water*mRho*mCp)+mT1;
		return (t2-mT1)/Math.log((mTs-mT1)/(mTs-t2));
	}
	public double getCondenserPress(double Q,double W){
		
	}
	public double temperatureSI2US(double t){return 1.8*t-459.67;}
	public double temperatureUS2SI(double t){return (t+459.67)/1.8;}
	public double pressureSI2US(double p){return p/3.38638e-2;}
	public double pressureUS2SI(double p){return p*3.38638e-2;}
	public double htfSI2US(double u){return u/5.678263;}
	public double htfUS2SI(double u){return u*5.678263;}
	public double velSI2US(double ws){return ws/0.3048;}
	public double velUS2SI(double ws){return ws*0.3048;}
	public double lengthSI2US(double d){return d/0.0254;}
	public double lengthUS2SI(double d){return d*0.0254;}
	
}
