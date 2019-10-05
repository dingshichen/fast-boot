package cn.dsc.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author dingShiChen
 * @since 2019/9/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {

	private String name;
	private Integer gender;
	private LocalDateTime expireTime;
}
