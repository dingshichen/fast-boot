package cn.dsc.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author dingShiChen
 * @since 2019/9/13
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "emp", schema = "mybatis_plus")
@DynamicInsert	//这两个注解一定要的，新增和修改时，如果没有那个属性没有值，则不拼接sql，就可以取到数据库默认值
@DynamicUpdate
public class EmpEntity {
	private long id;
	private Boolean valid;
	private String sex;
	private String name;
	private String phone;
	private Integer salary;
	private Date birthday;

//	private List<LeaveEntity> leaveEntities;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//一定要加，标识为自增长
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "VALID")
	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	@Basic
	@Column(name = "SEX")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Basic
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Basic
	@Column(name = "SALARY")
	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Basic
	@Column(name = "BIRTHDAY")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

//	@OneToMany(fetch = FetchType.LAZY)
//	public List<LeaveEntity> getLeaveEntities() {
//		return leaveEntities;
//	}
//
//	public void setLeaveEntities(List<LeaveEntity> leaveEntities) {
//		this.leaveEntities = leaveEntities;
//	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EmpEntity empEntity = (EmpEntity) o;
		return id == empEntity.id &&
				Objects.equals(valid, empEntity.valid) &&
				Objects.equals(sex, empEntity.sex) &&
				Objects.equals(name, empEntity.name) &&
				Objects.equals(phone, empEntity.phone) &&
				Objects.equals(salary, empEntity.salary) &&
				Objects.equals(birthday, empEntity.birthday);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, valid, sex, name, phone, salary, birthday);
	}
}
