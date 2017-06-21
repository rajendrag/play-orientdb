package com.rp.orientdb.example.query.impl

import com.orientechnologies.orient.core.command.OCommand
import com.orientechnologies.orient.core.sql.OCommandSQL
import com.rp.orientdb.example.query.UserAccessQueryHelper

/**
  * Created by RP on 6/20/17.
  */
class UserAccessQueryHelperImpl extends UserAccessQueryHelper {

  override def resourcesOfUserQuery() = {
    val query = """
    select expand(re) from (MATCH {class: User, where: (user_id = :userId)}.out("HAS_ACCESS")
          {as: re, while: ($depth < 2)} RETURN re ) where re.@class = 'Resource'
    """
    new OCommandSQL(query)
  }
}
