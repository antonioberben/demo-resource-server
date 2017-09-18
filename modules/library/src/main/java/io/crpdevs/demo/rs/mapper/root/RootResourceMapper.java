package io.crpdevs.demo.rs.mapper.root;


import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;
import io.crpdevs.demo.rs.persistence.entity.root.RootResource;
import io.crpdevs.demo.rs.representation.root.RootResourceInput;
import io.crpdevs.demo.rs.representation.root.RootResourceOutput;

import java.util.List;

@Mapper(withIoC = IoC.SPRING)
public interface RootResourceMapper {
    /**
     * to DTO.
     *
     * @param entity {@link RootResource}.
     * @return DTO
     */
    @Maps(
        withIgnoreMissing = IgnoreMissing.ALL
    )
    RootResourceOutput mapEntityTo(RootResource entity);

    /**
     * to Entity.
     *
     * @param entityInput {@link RootResource}.
     * @param entityDestiny {@link RootResource}.
     * @return DTO
     */
    @Maps(
        withIgnoreFields = {"id"}
    )
    RootResource mergeEntities(RootResource entityInput, RootResource entityDestiny);

    /**
     * to List of DTOs.
     *
     * @param entityList {@link List<RootResource>}
     * @return List of DTOs
     */
    List<RootResourceOutput> mapEntitiesTo(List<RootResource> entityList);

    /**
     * to Entity.
     *
     * @param inputDto {@link RootResourceInput}
     * @return Entity
     */
    @Maps(
        withIgnoreMissing = IgnoreMissing.SOURCE
    )
    RootResource mapRepresentationTo(RootResourceInput inputDto);
}
