cat - <<EOF >> /tmp/root.ldif
dn: dc=dep,dc=com
objectClass: dcObject
objectClass: organization
o: dep
dc: dep
EOF

cat - <<EOF >> /tmp/group.ldif
dn: ou=People,dc=dep,dc=com
ou: People
objectClass: organizationalUnit
objectClass: top
EOF

cat - <<EOF >> /tmp/add_user.ldif
dn: cn=depadmin,ou=People,dc=dep,dc=com
objectClass: inetOrgPerson
uid: depadmin
sn: depadmin
cn: depadmin
displayName: depadmin
employeeType: LDAP_ADMIN
userPassword: {SSHA}2GKW83bOOXjTkfi9zjTLvD3/24lghMoX
EOF

cat - <<EOF >> /tmp/add_user_1.ldif
dn: cn=test,ou=People,dc=dep,dc=com
objectClass: inetOrgPerson
uid: test
sn: test
cn: test
displayName: test
employeeType: LDAP_ADMIN
userPassword: {SSHA}2GKW83bOOXjTkfi9zjTLvD3/24lghMoX
EOF

ldapadd -x -w admin -D "cn=admin,dc=dep,dc=com" -H ldap://localhost:1389  -f /tmp/root.ldif
ldapadd -x -w admin -D "cn=admin,dc=dep,dc=com" -H ldap://localhost:1389  -f /tmp/group.ldif
ldapadd -x -w admin -D "cn=admin,dc=dep,dc=com" -H ldap://localhost:1389  -f /tmp/add_user.ldif
ldapadd -x -w admin -D "cn=admin,dc=dep,dc=com" -H ldap://localhost:1389  -f /tmp/add_user_1.ldif