package cn.dsc.hibernate.entity;

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
@Entity
@Table(name = "leave", schema = "jpa")
@DynamicInsert
@DynamicUpdate
public class LeaveEntity {
	private long id;
	private Boolean valid;
	private long empId;
	private Date createDate;

//	private EmpEntity empEntity;

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
	@Column(name = "EMP_ID")
	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	@Basic
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

//	@ManyToOne
//	@JoinColumn(name = "EMP_ID", foreignKey = @ForeignKey(name = "emp"))
//	public EmpEntity getEmpEntity() {
//		return empEntity;
//	}
//
//	public void setEmpEntity(EmpEntity empEntity) {
//		this.empEntity = empEntity;
//	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LeaveEntity that = (LeaveEntity) o;
		return id == that.id &&
				empId == that.empId &&
				Objects.equals(valid, that.valid) &&
				Objects.equals(createDate, that.createDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, valid, empId, createDate);
	}
}
