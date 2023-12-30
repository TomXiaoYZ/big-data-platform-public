#/bin/sh

sleep 60
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

ldapadd -c -x -W $ADMIN_PASSWORD -D "$ADMIN_DN" -b "$DOMAIN" -f /tmp/add_user.ldif

echo Success