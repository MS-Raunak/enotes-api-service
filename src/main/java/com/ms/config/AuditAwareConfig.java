package com.ms.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditAwareConfig implements AuditorAware<Integer>{

	@Override
	public Optional<Integer> getCurrentAuditor() {
		//current user implementation
		//....
		
		return Optional.of(1);
	}

}
