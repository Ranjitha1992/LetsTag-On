package com.letstagon.facade.reverse.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.letstagon.dao.model.Address;
import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.Party;
import com.letstagon.facade.dto.OpportunityDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class OpportunityDTOConverter.
 */
@Component
public class OpportunityDTOConverter implements Converter<OpportunityDTO, Opportunity> {

	/** The mapper. */
	@Autowired
	private DozerBeanMapper mapper;

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Opportunity convert(OpportunityDTO source) {
		if (source == null) {
			return null;
		}

		Opportunity dest = new Opportunity();
		dest.setId(source.getId());
		dest.setBannerImage(source.getBannerImage());
		dest.setDateStart(source.getDateStart());
		dest.setDateEnd(source.getDateEnd());
		dest.setDescription(source.getDescription());
		dest.setName(source.getName());
		dest.setType(source.getType());
		dest.setLatLong(source.getLatLong());
		if (source.getAddressBean() != null) {
			dest.setAddressBean(mapper.map(source.getAddressBean(), Address.class));
		}

		if (source.getCreatedBy() != null) {
			dest.setCreatedByParty(this.mapper.map(source.getCreatedBy(), Party.class));
		}

		dest.setParentProgram(this.convert(source.getParentProgram()));

		return dest;
	}

}
