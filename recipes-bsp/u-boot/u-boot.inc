DESCRIPTION = "U-Boot - the Universal Boot Loader"
HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"
PRIORITY = "optional"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4c6cde5df68eff615d36789dc18edd3b"

PROVIDES = "virtual/bootloader"

DEPENDS = "mtd-utils"

PACKAGE_ARCH = "${MACHINE_ARCH}"
PARALLEL_MAKE=""

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX}"

UBOOT_MACHINE ?= "${MACHINE}"
UBOOT_BINARY ?= "u-boot.bin"
UBOOT_IMAGE ?= "u-boot-${MACHINE}-${PV}-${PR}.bin"
UBOOT_SYMLINK ?= "u-boot-${MACHINE}.bin"
UBOOT_MAKE_TARGET ?= "all"

do_configure () {
	echo	"Nothing to configure here"
}

do_compile () {
	for board in ${UBOOT_MACHINES} ; do
		if ! [ `grep ${board}_config Makefile | wc -c` == 0 ] ; then
			mkdir -p binaries/${board}
			oe_runmake O=binaries/${board} distclean
			oe_runmake O=binaries/${board} ${board}_config
			oe_runmake O=binaries/${board} all
		fi
	done
}

do_install () {
	install -d ${D}/boot
	for board in ${UBOOT_MACHINES} ; do
		if ! [ `grep ${board}_config ${S}/Makefile | wc -c` == 0 ] ; then
			install ${S}/binaries/${board}/${UBOOT_BINARY} ${D}/boot/u-boot-${board}-${PV}-${PR}.bin
		fi
		if [ -e ${WORKDIR}/binaries/${board}/fw_env.config ] ; then
			if ! [ -e ${D}${sysconfdir}/fw_env.config ] ; then

				install -d ${D}${sysconfdir}
				install -m 644 ${WORKDIR}/binaries/${board}/fw_env.config	${D}${sysconfdir}/fw_env.config

				install -d ${D}${base_sbindir}
				install -m 755 ${S}/binaries/${board}/tools/env/fw_printenv	${D}${base_sbindir}/fw_printenv
				install -m 755 ${S}/binaries/${board}/tools/env/fw_printenv	${D}${base_sbindir}/fw_setenv
			fi
		fi
	done
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
	if ! [ "x${UBOOT_MACHINES}" == "x" ] ; then
		install -d ${DEPLOY_DIR_IMAGE}/u-boot
		for board in ${UBOOT_MACHINES} ; do
			install ${S}/binaries/${board}/${UBOOT_BINARY}	${DEPLOY_DIR_IMAGE}/u-boot/${board}-u-boot-${PV}-${PR}.bin
			package_stagefile_shell				${DEPLOY_DIR_IMAGE}/u-boot/${board}-u-boot-${PV}-${PR}.bin
		done
	else
		install ${S}/${UBOOT_BINARY} ${DEPLOY_DIR_IMAGE}/${UBOOT_IMAGE}
		package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${UBOOT_IMAGE}
		cd ${DEPLOY_DIR_IMAGE}
		rm -f ${UBOOT_SYMLINK}
		ln -sf ${UBOOT_IMAGE} ${UBOOT_SYMLINK}
		package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${UBOOT_SYMLINK}
	fi
}
do_deploy[dirs] = "${S}"

