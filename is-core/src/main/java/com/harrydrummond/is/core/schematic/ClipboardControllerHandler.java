package com.harrydrummond.is.core.schematic;

import com.harrydrummond.is.core.annotations.AnnotationUtils;
import com.harrydrummond.is.core.annotations.ClipboardControllerRequestFormData;
import com.harrydrummond.is.core.annotations.HandlerRequest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Handler for clipboard controller
 *
 * <p>ClipboardController type is requested from specified annotated classes
 * and prioritized based on given priority integer passed through annotation</p>
 */
public class ClipboardControllerHandler {

    private ClipboardController clipboardController;

    public ClipboardControllerHandler() {
        clipboardController = requestPrimaryClipboardController();
    }

    /**
     * Retrieves the primary clipboard controller. This is determined by the {@link ClipboardControllerHandler#requestPrimaryClipboardController()}
     * or {@link ClipboardControllerHandler#useThirdPartyClipboardController(ClipboardController)}
     * @return Primary clipboard controller if present. Null if no valid controller was found within classpath upon class creation.
     */
    public ClipboardController getPrimaryClipboardController() {
        return clipboardController;
    }

    /**
     * Scans through internal package hierarchy and finds annotated classes which
     * extend type {@link ClipboardController}. Then, retrieves ClipboardController instance
     * from highest priority class that doesn't return null
     * @return ClipboardController with highest priority out of all found classes. Null if no classes found or
     * no instance returned.
     */
    public ClipboardController requestPrimaryClipboardController() {
        List<ClipboardControllerRequestFormData> requestForms = AnnotationUtils.getAllRequesteeClipboardControllers("com.harrydrummond.is");
        requestForms = requestForms.stream().sorted(Comparator.comparingInt(x -> x.getPriority())).collect(Collectors.toList());
        Collections.reverse(requestForms);

        for(ClipboardControllerRequestFormData data : requestForms) {
            ClipboardController clip = data.callMethod();
            if (clip == null) continue;
            return clip;
        }
        return null;
    }

    /**
     * Sets the primary clipboard controller to that of which is passed through the parameters.
     * Class must be annotated with {@link HandlerRequest} and implement {@link ClipboardController}.
     * @return if successfully updated third party clipboard controller
     */
    public boolean useThirdPartyClipboardController(final ClipboardController clipboardController) {
        this.clipboardController = checkNotNull(clipboardController);
        return true;
    }
}