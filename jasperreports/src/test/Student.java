package test;
import java.util.List;
import java.util.ArrayList;

public class Student
{
    public String id;
    public String name;
    public String file_id;
    public String file_title;
    public String category;
    public List<Student> districtList;
    public String  district;
    public List<Student> constituencyList;
    public String constituency;
    public List<Student> dataList;
    public String title;
    public String description;
    
	
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
	
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
	
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public String getFile_title() {
		return file_title;
	}
	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}
	public static List<Student> getStudentList()
    {
        List<Student> students = new ArrayList<Student>();
        try
        {
            Student student = new Student();
            student.setId("1");
            student.setName("Steve");
            student.setFile_id("1");
            student.setFile_title("Steve");
            students.add(student);
            student = new Student();
            student.setId("2");
            student.setName("Mark");
            student.setFile_id("2");
            student.setFile_title("Mark");
            students.add(student);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return students;
    }
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<Student> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(List<Student> districtList) {
		this.districtList = districtList;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public List<Student> getConstituencyList() {
		return constituencyList;
	}
	public void setConstituencyList(List<Student> constituencyList) {
		this.constituencyList = constituencyList;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Student> getDataList() {
		return dataList;
	}
	public void setDataList(List<Student> dataList) {
		this.dataList = dataList;
	}
	
}
