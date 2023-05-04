package csen1002.tests.task6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task6.CfgFirstFollow;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task6TestsBatch5 {

	@Test
	public void testCfg1First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;Z;J;K;D;U#a;c;d;j;o;u;w#S/J,JJ,JcS,uSKj,uSZcS;Z/JKKJ,K,uDcZJ,uKoS,wDSjJ,wUDU;J/UjU,e,uS;K/J,JwZSZ,KSo;D/D,J,JcSSD,ZZwUd,e,jJS;U/w,wJaJJ");
		assertEquals("S/ceuw;Z/ceouw;J/euw;K/ceouw;D/cejouw;U/w", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg1Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;Z;J;K;D;U#a;c;d;j;o;u;w#S/J,JJ,JcS,uSKj,uSZcS;Z/JKKJ,K,uDcZJ,uKoS,wDSjJ,wUDU;J/UjU,e,uS;K/J,JwZSZ,KSo;D/D,J,JcSSD,ZZwUd,e,jJS;U/w,wJaJJ");
		assertEquals("S/$acdjouw;Z/cjouw;J/$acdjouw;K/cjouw;D/cjuw;U/$acdjouw", cfgFirstFollow.follow());
	}

	@Test
	public void testCfg2First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;U;X;F;T;G#d;j;k;m;s;u;v#S/F,TFS,vFmSs;U/F,SkF,e,jS,kU;X/GuT,T,US,XFkGu,e;F/T,kGm,vF;T/SmGv,e,k,kT;G/SS,XGSu,d,dFuS,kGUGd,v");
		assertEquals("S/ekmv;U/ejkmv;X/dejkmuv;F/ekmv;T/ekmv;G/dejkmuv", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg2Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;U;X;F;T;G#d;j;k;m;s;u;v#S/F,TFS,vFmSs;U/F,SkF,e,jS,kU;X/GuT,T,US,XFkGu,e;F/T,kGm,vF;T/SmGv,e,k,kT;G/SS,XGSu,d,dFuS,kGUGd,v");
		assertEquals("S/$djkmsuv;U/djkmuv;X/djkmuv;F/$djkmsuv;T/$djkmsuv;G/djkmuv", cfgFirstFollow.follow());
	}

	@Test
	public void testCfg3First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;H;J;F;V;Z;Q#a;j;n;o;p;r;y#S/FHn,HoF,J,QJ,SpS,pHr;H/J,JVVH,JrFn,QJS,jF;J/J,nHjF,yFr;F/SFoSS,VFHoF,a,e;V/F,HJ,J,Jo,e,j,nHaHJ,nZ,rFJy;Z/H,JaQF,S,V,jJV,r,rHrVF,y;Q/F,SVQaH,ZZ");
		assertEquals("S/ajnpry;H/ajnpry;J/ny;F/aejnpry;V/aejnpry;Z/aejnpry;Q/aejnpry", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg3Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;H;J;F;V;Z;Q#a;j;n;o;p;r;y#S/FHn,HoF,J,QJ,SpS,pHr;H/J,JVVH,JrFn,QJS,jF;J/J,nHjF,yFr;F/SFoSS,VFHoF,a,e;V/F,HJ,J,Jo,e,j,nHaHJ,nZ,rFJy;Z/H,JaQF,S,V,jJV,r,rHrVF,y;Q/F,SVQaH,ZZ");
		assertEquals("S/$ajnopry;H/ajnopry;J/$ajnopry;F/$ajnopry;V/ajnpry;Z/ajnpry;Q/ajnpry", cfgFirstFollow.follow());
	}

	@Test
	public void testCfg4First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;K;L;E;T;G;M#h;o;s;t;x;z#S/GxT,MsTSt,Tx,o,t;K/EG,EsK,G,KzTxE,L,MML,e,t;L/GTs,K,xKGh;E/E,L,MKs,MsMTT,SzMTs;T/KKKLs,Kh,SM,e;G/G,oEKs,zExE;M/xM,z");
		assertEquals("S/hostxz;K/ehostxz;L/ehostxz;E/ehostxz;T/ehostxz;G/oz;M/xz", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg4Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;K;L;E;T;G;M#h;o;s;t;x;z#S/GxT,MsTSt,Tx,o,t;K/EG,EsK,G,KzTxE,L,MML,e,t;L/GTs,K,xKGh;E/E,L,MKs,MsMTT,SzMTs;T/KKKLs,Kh,SM,e;G/G,oEKs,zExE;M/xM,z");
		assertEquals("S/$txz;K/hostxz;L/hostxz;E/hostxz;T/$hostxz;G/hostxz;M/$hostxz", cfgFirstFollow.follow());
	}

	@Test
	public void testCfg5First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;Z;W;X;E;R;J#b;h;i;o;q;x;z#S/XWEzS,XXRXW;Z/JEXXb,JWxSR,SoWo,W,ZbZ;W/ExZ,z;X/Z,e,iSX,xE;E/JZ,R,S,W,XWZxW,ZJ,b,xZ;R/S,SbSS,e,i,oRi;J/E,JX,WhEE,hXRzS,xSiWq,xXSh");
		assertEquals("S/bhioxz;Z/bhioxz;W/bhioxz;X/behioxz;E/behioxz;R/behioxz;J/behioxz", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg5Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;Z;W;X;E;R;J#b;h;i;o;q;x;z#S/XWEzS,XXRXW;Z/JEXXb,JWxSR,SoWo,W,ZbZ;W/ExZ,z;X/Z,e,iSX,xE;E/JZ,R,S,W,XWZxW,ZJ,b,xZ;R/S,SbSS,e,i,oRi;J/E,JX,WhEE,hXRzS,xSiWq,xXSh");
		assertEquals("S/$bhioqxz;Z/$bhioqxz;W/$bhioqxz;X/bhioxz;E/bhioxz;R/$bhioqxz;J/bhioxz", cfgFirstFollow.follow());
	}

}