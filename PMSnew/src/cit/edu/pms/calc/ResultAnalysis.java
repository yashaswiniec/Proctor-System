package cit.edu.pms.calc;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import cit.edu.pms.entity.StudentResult;
import cit.edu.pms.entity.SubjectCode;
import cit.edu.pms.service.StudentService;

public class ResultAnalysis {
	private ArrayList<Integer> internalMarks;
	private ArrayList<Integer> externalMarks;
	private ArrayList<Integer> totalMarks;
	private ArrayList<Integer> total;
	private ArrayList<String> remarks;
	private String usN, dept;
	private ArrayList<String> subcode;
	private ArrayList<Integer> intMaxmarks;
	private ArrayList<Integer> extMaxmarks;
	SubjectCode subjectcode;
	private int semestr;	
	StudentService service = new StudentService();
	StudentResult result;
	
	public ResultAnalysis(ArrayList<Integer> internalMarks,
			ArrayList<Integer> externalMarks, ArrayList<Integer> totalMarks,
			String usN, int sem) {
		super();
		this.internalMarks = internalMarks;
		this.externalMarks = externalMarks;
		this.totalMarks = totalMarks;
		this.usN = usN;
		this.semestr=sem;
	}

	public ArrayList<Integer> calculateTotal()
	{
		try
		{
			int totalmarks;
			total = new ArrayList<Integer>();
			System.out.println("result"+internalMarks);
			System.out.println("result"+externalMarks);
			if(semestr==8)
			{
				for(int i=0;i<5;i++)
				{
					totalmarks = internalMarks.get(i)+externalMarks.get(i);				
					total.add(totalmarks);
				}
					totalmarks = externalMarks.get(5);
					total.add(totalmarks);
			}
			else
			{
				for(int i=0;i<8;i++)
				{
					totalmarks = internalMarks.get(i)+externalMarks.get(i);				
					total.add(totalmarks);
					
				}
			}		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"No result to show","Message", JOptionPane.ERROR_MESSAGE);
		}
		return total;
	}
	
	public ArrayList<String> findRemarks()
	{
		try
		{			
			String remark;
			remarks= new ArrayList<String>();
			if(semestr==8)
			{
				for(int i=0;i<4;i++)
				{
					if(externalMarks.get(i)<35)
					{
						remark="Fail";
						remarks.add(remark);
					}
					else if(total.get(i)>=0&&total.get(i)<50)
					{
						remark = "FAIL";
						remarks.add(remark);
					}
					else if(total.get(i)>=75&&total.get(i)<88)
					{
						remark = "FC";
						remarks.add(remark);
					}
					else if(total.get(i)>=88&&total.get(i)<=125)
					{
						remark="FCD";
						remarks.add(remark);
					}
					else if(total.get(i)>=50&&total.get(i)<=74)
					{
						remark = "SC";
						remarks.add(remark);
					}
				}
				if(externalMarks.get(4)<35)
				{
					remark = "Fail";
					remarks.add(remark);
				}
				else if(total.get(4)>=70&&total.get(4)<120)
				{
					remark = "SC";
					remarks.add(remark);						
				}
				else if(total.get(4)>=120&&total.get(4)<140)
				{
					remark = "FC";
					remarks.add(remark);						
				}
				else if(total.get(4)>=140&&total.get(5)<=200)
				{
					remark = "FCD";
					remarks.add(remark);						
				}
				else if(total.get(4)<70)
				{
					remark = "Fail";
					remarks.add(remark);						
				}
					if(externalMarks.get(5)<18)
					{
						remark = "Fail";
						remarks.add(remark);
					}
					else if(total.get(5)>=18&&total.get(5)<30)
					{
						remark = "SC";
						remarks.add(remark);						
					}
					else if(total.get(5)>=30&&total.get(5)<35)
					{
						remark = "FC";
						remarks.add(remark);						
					}
					else if(total.get(5)>=35&&total.get(5)<=50)
					{
						remark = "FCD";
						remarks.add(remark);						
					}
					else if(total.get(5)<18)
					{
						remark = "Fail";
						remarks.add(remark);						
					}
					
			}
				
			else
			{				
				for(int i=0;i<6;i++)
				{
					if(externalMarks.get(i)<35)
					{
						remark="Fail";
						remarks.add(remark);
					}
					else if(total.get(i)>=0&&total.get(i)<50)
					{
						remark = "FAIL";
						remarks.add(remark);
					}
					else if(total.get(i)>=75&&total.get(i)<88)
					{
						remark = "FC";
						remarks.add(remark);
					}
					else if(total.get(i)>=88&&total.get(i)<=125)
					{
						remark="FCD";
						remarks.add(remark);
					}
					else if(total.get(i)>=50&&total.get(i)<=74)
					{
						remark = "SC";
						remarks.add(remark);
						
					}
				}
					for(int i=6;i<8;i++)
					{
						if(externalMarks.get(i)<18)
						{
							remark="Fail";
							remarks.add(remark);
						}
						
						if(total.get(i)>=18 && total.get(i)<45)
						{
							remark = "SC";
							remarks.add(remark);
						}
						if(total.get(i)>=45 && total.get(i)<53)
						{
							remark = "FC";
							remarks.add(remark);
						}
						if(total.get(i)>=53 && total.get(i)<=75)
						{
							remark = "FCD";
							remarks.add(remark);
						}
						
					}
				}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"No result to show","Message", JOptionPane.ERROR_MESSAGE);
		}
		return remarks;
	}
	 
	public int gettotalmaxmarks()
	{
		int ttlmax=0;
		dept = service.getDept(usN);
		subcode = new ArrayList<String>();
		intMaxmarks = new ArrayList<Integer>();
		extMaxmarks = new ArrayList<Integer>();
		subjectcode = new SubjectCode(subcode, intMaxmarks, extMaxmarks);
		subjectcode = service.insertCode(semestr, dept);
		try
		{
			for(int i=0;i<subjectcode.getSubcode().size();i++)
			{
				ttlmax=ttlmax+subjectcode.getIntMaxMarks().get(i)+ subjectcode.getExtMaxMarks().get(i);
			}
						
		}
		
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"No result to show","Message", JOptionPane.ERROR_MESSAGE);
		}
		
		return ttlmax;		
	}
	
	public int gettotalexternalmarks()
	{
		int totalmarks=0;
		dept = service.getDept(usN);
		subcode = new ArrayList<String>();
		intMaxmarks = new ArrayList<Integer>();
		extMaxmarks = new ArrayList<Integer>();
		subjectcode = new SubjectCode(subcode, intMaxmarks, extMaxmarks);
		subjectcode = service.insertCode(semestr, dept);
		try
		{			
			for(int i=0;i<subjectcode.getSubcode().size();i++)
			{
				totalmarks=totalmarks+totalMarks.get(i);
			}		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"No result to show","Message", JOptionPane.ERROR_MESSAGE);
			
		}
		return totalmarks;
	}
	
		public float gettotalpercentage()
		{
			float percent;
			int maxmarks = gettotalmaxmarks();
			int marksObtained=gettotalexternalmarks();
			percent = ((float)marksObtained/(float)maxmarks)*100;
			return percent;
		}
		
		
		public String getpercentageremarks()
		{
				float percent;			
				String remark = null;			
				percent = gettotalpercentage();
				ArrayList<String> subRemark = new ArrayList<String>();
				internalMarks = new ArrayList<Integer>();
				externalMarks = new ArrayList<Integer>();
				total = new ArrayList<Integer>();
				remarks = new ArrayList<String>();
				result = new StudentResult(internalMarks, externalMarks, total, remarks);
				result = service.getAllResult(usN, semestr);
				subRemark = result.getRemarks();
				dept = service.getDept(usN);
				subcode = new ArrayList<String>();
				intMaxmarks = new ArrayList<Integer>();
				extMaxmarks = new ArrayList<Integer>();
				subjectcode = new SubjectCode(subcode, intMaxmarks, extMaxmarks);
				subjectcode = service.insertCode(semestr, dept);
				boolean fail = false;
				try
				{
					
					for(int i=0;i<subjectcode.getSubcode().size();i++)
					{
						if(subRemark.get(i)!=null)
						{
							if(subRemark.get(i).equalsIgnoreCase("Fail"))
							{
								fail = true;
							}
						}
						
					}
					if(fail)
					{
						remark = "Fail";
					}
					else
					{
						if(percent>=70)
						{
							remark = "FCD";					
						}
						else if (percent>=60 && percent<70)
						{
							remark = "FC";
						}
						else if(percent>=35 && percent<60)
						{
							remark="SC";
						}				
					}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				//JOptionPane.showMessageDialog(null,"No result to show","Message", JOptionPane.ERROR_MESSAGE);
			}
				return remark;			
		}
		
}

	
	
	


