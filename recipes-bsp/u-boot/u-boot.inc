DESCRIPTION = "U-Boot - the Universal Boot Loader"
HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"
PRIORITY = "optional"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4c6cde5df68eff615d36789dc18edd3b"

PROVIDES = "virtual/bootloader"

DEPENDS = "mtd-utils"

PACKAGE_ARCH = "${MACHINE_ARCH}"
# PARALLEL_MAKE=""

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX}"

UBOOT_MACHINE ?= "${MACHINE}"
UBOOT_BINARY ?= "u-boot.bin"
UBOOT_IMAGE ?= "u-boot-${MACHINE}-${PV}-${PR}.bin"
UBOOT_MAKE_TARGET ?= "all"

do_configure () {
	echo	"Nothing to configure here"
}

do_compile () {
	echo "MACHINES: ${UBOOT_MACHINES}" >> ${WORKDIR}/board_files.txt
	for board in ${UBOOT_MACHINES} ; do
		if ! [ `grep ${board}_config Makefile | wc -c` == 0 ] ; then
			unset LDFLAGS
			unset CFLAGS
			unset CPPFLAGS
			mkdir -p binaries/${board}
			oe_runmake O=binaries/${board} distclean
			oe_runmake O=binaries/${board} ${board}_config
			oe_runmake O=binaries/${board} all
		fi
	done
#	oe_runmake tools env
}

do_install () {
	install -d ${D}/boot
	for board in ${UBOOT_MACHINES} ; do
		install ${S}/binaries/${board}/${UBOOT_BINARY} ${D}/boot/${board}-u-boot-${PV}-${PR}.bin
	done
	# Install utilities (Use the last $board)
	if [ -e ${WORKDIR}/binaries/${board}/fw_env.config ] ; then
		if ! [ -e ${D}${sysconfdir}/fw_env.config ] ; then
			install -d ${D}${sysconfdir}
			install -m 644 ${WORKDIR}/binaries/${board}/fw_env.config	${D}${sysconfdir}/fw_env.config
			install -d ${D}${base_sbindir}
			install -m 755 ${S}/binaries/${board}/tools/env/fw_printenv	${D}${base_sbindir}/fw_printenv
			install -m 755 ${S}/binaries/${board}/tools/env/fw_printenv	${D}${base_sbindir}/fw_setenv
		fi
	fi
}

FILES_${PN} = "/boot"

# no gnu_hash in uboot.bin, by design, so skip QA
INSANE_SKIP_${PN} = True

PACKAGES += "${PN}-fw-utils"
FILES_${PN}-fw-utils = "${sysconfdir} ${base_sbindir}"

# u-boot doesn't use LDFLAGS for fw files, needs to get fixed, but until then:
INSANE_SKIP_${PN}-fw-utils = True

inherit deploy

addtask deploy before do_package after do_compile

do_deploy () {
	install -d ${DEPLOY_DIR_IMAGE}
	install -d ${DEPLOY_DIR_IMAGE}/u-boot
	for board in ${UBOOT_MACHINES} ; do
		install ${S}/binaries/${board}/${UBOOT_BINARY}		${DEPLOY_DIR_IMAGE}/u-boot/${board}-u-boot-${PV}-${PR}.bin
		install ${S}/binaries/${board}/include/autoconf.mk	${DEPLOY_DIR_IMAGE}/u-boot/${board}-u-boot-${PV}-${PR}-autoconf.mk		
	done
}


