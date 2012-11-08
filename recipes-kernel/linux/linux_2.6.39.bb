DESCRIPTION = "Linux kernel for AT91 processors"
PR = "r0"

PACKAGES_DYNAMIC += "kernel-module-*"
PACKAGES_DYNAMIC += "kernel-image-*"


# Specify the commandline for your device
# Used by "linux.inc", so it must be before the include of that file.
# Should really be in machine description.
# Boot from mmc
CMDLINE_at91sam9263ek = "mem=64M console=ttyS0,115200 root=/dev/mmcblk0p1 rootfstype=ext2 rootdelay=5"
CMDLINE_ariag25 = "mem=64M console=ttyS0,115200 root=/dev/mmcblk0p1 rootfstype=ext2 rootdelay=5"
# Boot from nfs
#CMDLINE_at91sam9263ek = "mem=64M console=ttyS0,115200 root=301 root=/dev/nfs nfsroot=172.20.3.1:/data/at91 ip=172.20.0.5:::255.255.0.0"

require linux.inc

S = "${WORKDIR}/linux-${PV}"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "at91sam9g15ek|at91sam9g25ek|at91sam9g35ek|at91sam9x25ek|at91sam9x35ek|ariag25"
FAST_MIRROR = "ftp://ftp.sunet.se/pub/Linux/kernel.org"
AT91_PATCH	= "${PV}-at91-exp2"

# FILESPATHPKG_prepend = "linux-2.6.39:"

SRC_URI = " \
	${FAST_MIRROR}/linux/kernel/v2.6/linux-${PV}.tar.bz2;name=kernel \
	"

#	file://wvga/logo_linux_clut224.ppm \
SRC_URI_append_at91sam9g35ek = " \
	file://defconfig \
	"

SRC_URI_append_at91sam9g25ek = " \
	file://defconfig \
	file://ariag25.patch \
	"

SRC_URI_append_ariag25 = " \
	file://defconfig \
	file://ariag25.patch \
	"

SRC_URI[kernel.md5sum] = "1aab7a741abe08d42e8eccf20de61e05"
SRC_URI[kernel.sha256sum] = "584d17f2a3ee18a9501d7ff36907639e538cfdba4529978b8550c461d45c61f6"

