DESCRIPTION = "Linux kernel for AT91 processors"
PR = "r0"

PACKAGES_DYNAMIC += "kernel-module-*"
PACKAGES_DYNAMIC += "kernel-image-*"


# Specify the commandline for your device
# Used by "linux.inc", so it must be before the include of that file.
# Should really be in machine description.
# Boot from mmc
CMDLINE_at91sam9263ek = "mem=64M console=ttyS0,115200 root=/dev/mmcblk0p1 rootfstype=ext2 rootdelay=5"
# Boot from nfs
#CMDLINE_at91sam9263ek = "mem=64M console=ttyS0,115200 root=301 root=/dev/nfs nfsroot=172.20.3.1:/data/at91 ip=172.20.0.5:::255.255.0.0"

require linux.inc

S = "${WORKDIR}/linux-${PV}"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "at91sam9g15ek|at91sam9g25ek|at91sam9g35ek|at91sam9x25ek|at91sam9x35ek"
FAST_MIRROR = "ftp://ftp.sunet.se/pub/Linux/kernel.org"
AT91_PATCH	= "${PV}-at91-exp2"

# FILESPATHPKG_prepend = "linux-2.6.39:"

SRC_URI = " \
	${FAST_MIRROR}/linux/kernel/v2.6/linux-${PV}.tar.bz2;name=kernel \
	ftp://ftp.emagii.com/pub/linux/kernel/v2.6/${AT91_PATCH}.tar.bz2;name=at91exp2 \
	file://defconfig \
	file://wvga/logo_linux_clut224.ppm \
	"

SRC_URI_append_at91sam9g35ek = " \
	"

# do_configure_prepend() {
#	yes '' | oe_runmake at91sam9x5ek_defconfig
#	install -m 0644 ${S}/.config ${WORKDIR}/defconfig
#}

do_apply_at91exp() {
	cd	${S}
	echo	AT91_PATCH=${AT91_PATCH}				>	${WORKDIR}/patches.txt
	echo	PATCHDIR="${WORKDIR}/${AT91_PATCH}"			>>	${WORKDIR}/patches.txt
	cat	${WORKDIR}/${AT91_PATCH}/series				>>	${WORKDIR}/patches.txt
	for p in `cat ${WORKDIR}/${AT91_PATCH}/series` ; do
		echo	"Applying $p"					>>	${WORKDIR}/patches.txt
		cat ${WORKDIR}/2.6.39-at91-exp2/$p	| patch -p1	| tee -a	${WORKDIR}/patches2.txt
	done
#	mv	${WORKDIR}/wvga/logo_linux_clut224.ppm	${WORKDIR}/logo_linux_clut224.ppm
}

addtask apply_at91exp after do_unpack before do_patch

SRC_URI[kernel.md5sum] = "1aab7a741abe08d42e8eccf20de61e05"
SRC_URI[kernel.sha256sum] = "584d17f2a3ee18a9501d7ff36907639e538cfdba4529978b8550c461d45c61f6"

# 2.6.39-at91-exp
SRC_URI[at91exp.md5sum] = "5ef5bcb680a4f799a7bd16b2dc62d157"
SRC_URI[at91exp.sha256sum] = "01fe8819ab40086aa4d73d4ea777db93d4354723a8a43133be60812f50779898"

# 2.6.39-at91-exp2
SRC_URI[at91exp2.md5sum] = "48afbbb190c0b07e1d76d8a601c753bc"
SRC_URI[at91exp2.sha256sum] = "c9a90dc3e376fd326323907adb87213ab1e88b7d6c8aff0f708fd10a52fbd316"


