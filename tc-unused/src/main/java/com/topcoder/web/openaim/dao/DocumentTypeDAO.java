package com.topcoder.web.openaim.dao;

import com.topcoder.web.openaim.model.DocumentType;

import java.util.List;

/**
 * @author dok
 * @version $Revision: 68803 $ Date: 2005/01/01 00:00:00
 *          Create Date: Aug 1, 2006
 */
public interface DocumentTypeDAO {
    DocumentType find(Integer id);

    List getDocumentTypes();
}
