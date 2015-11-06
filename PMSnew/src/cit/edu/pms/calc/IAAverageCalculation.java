package cit.edu.pms.calc;

import java.util.ArrayList;

public class IAAverageCalculation {
	private ArrayList<Float> iaAverage = new ArrayList<Float>();
	private ArrayList<Integer> ia1 = new ArrayList<Integer>();
	private ArrayList<Integer> ia2 = new ArrayList<Integer>();
	private ArrayList<Integer> ia3 = new ArrayList<Integer>();
	private int semester;
	
	public IAAverageCalculation(ArrayList<Integer> ia1, ArrayList<Integer> ia2,
			ArrayList<Integer> ia3,int sem) {
		super();
		this.ia1 = ia1;
		this.ia2 = ia2;
		this.ia3 = ia3;
		this.semester=sem;
	}
	
	
	public ArrayList<Float> calculateavg()
	{
		iaAverage = new ArrayList<Float>();
		float avg = 0;
		int iA1,iA2,iA3;
		
		if(semester==8)
		{
			for(int i=0;i<4;i++)
			{
				iA1 = ia1.get(i);
				iA2 = ia2.get(i);
				iA3 = ia3.get(i);
				if( iA1>iA3 && iA2>iA3)
				{
					avg =(int) Math.ceil((float)(iA1+iA2)/2);
					iaAverage.add(avg);
				}
				else if(iA1>iA2 && iA3>iA2)
				{
					avg=(int) Math.ceil((float)(iA1+iA3)/2);
					iaAverage.add(avg);
				}
				else if(iA2>iA1 && iA3>iA1)
				{
					avg = (int) Math.ceil((float)(iA2+iA3)/2);
					iaAverage.add(avg);
				}
				else if(iA1==iA2||iA2==iA3)
				{
					avg =(int) Math.ceil((float)(iA1+iA3)/2);
					iaAverage.add(avg);
				}
				else if(iA1==0&&iA2==0&&iA3==0)
				{
					avg=0;
					iaAverage.add(avg);
				}
					
			}
			
			for( int i=4;i<6;i++)
			{
				avg = ia1.get(i);
				iaAverage.add(avg);				
			}
			return iaAverage;
		}
		
		else
		{		
			for(int i=0;i<6;i++)
			{			
				iA1 = ia1.get(i);
				iA2 = ia2.get(i);
				iA3 = ia3.get(i);
				if( iA1>iA3 && iA2>iA3)
				{
					avg =(int) Math.ceil((float)(iA1+iA2)/2);
					iaAverage.add(avg);
				}
				else if(iA1>iA2 && iA3>iA2)
				{
					avg=(int) Math.ceil((float)(iA1+iA3)/2);
					iaAverage.add(avg);
				}
				else if(iA2>iA1 && iA3>iA1)
				{
					avg = (int) Math.ceil((float)(iA2+iA3)/2);
					iaAverage.add(avg);
				}
				else if(iA1==iA2||iA2==iA3)
				{
					
					avg =(int) Math.ceil((float)(iA1+iA3)/2);
					iaAverage.add(avg);
				}
				else if(iA1==0&&iA2==0&&iA3==0)
				{
					avg=0;
					iaAverage.add(avg);
				}
							
			}
			for(int i=6;i<8;i++)
				{
					avg = ia1.get(i);
					iaAverage.add(avg);
				}
			return iaAverage;
		}
	
	}
	
	}
	
	
	
	


