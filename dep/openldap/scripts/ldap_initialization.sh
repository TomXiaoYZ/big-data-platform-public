#/bin/sh

export ENCRYPTED_PASSWORD=$(slappasswd -s "$USER_PASSWORD")
cat - <<EOF >> /tmp/add_user.ldif
dn: cn=$USER_CN,ou=People,$DOMAIN
objectClass: inetOrgPerson
uid: $USER_CN
sn: $USER_CN
cn: $USER_CN
displayName: $USER_CN
employeeType: LDAP_ADMIN
userPassword: $ENCRYPTED_PASSWORD
EOF

cat /tmp/add_user.ldif

ldapadd -x -w $ADMIN_PASSWORD -D "$ADMIN_DN" -H ldap://openldap:389  -f /tmp/add_user.ldif

echo Success

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
userPassword: {SSHA}dA5U39N681C742yY4DVyaHeMjuGuK2Qv
EOF

ldapadd -x -w admin -D "cn=admin,dc=dep,dc=com" -H ldap://localhost:1389  -f /tmp/root.ldif
ldapadd -x -w admin -D "cn=admin,dc=dep,dc=com" -H ldap://localhost:1389  -f /tmp/group.ldif
ldapadd -x -w admin -D "cn=admin,dc=dep,dc=com" -H ldap://localhost:1389  -f /tmp/add_user.ldif